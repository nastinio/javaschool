package com.nastinio.spring.service;

import com.nastinio.spring.dao.OptionCellularDAO;
import com.nastinio.spring.enums.CorrelationType;
import com.nastinio.spring.enums.ExtraOptionStatus;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.OptionCellular;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OptionCellularService {
    @Autowired
    OptionCellularDAO optionCellularDAO;

    @Autowired
    ContractService contractService;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Методы для получения всех взаимосвязей на опции
    //Получение необходимых опций
    public Set<OptionCellular> getAllJointlyOptions(OptionCellular option) {
        //Возьмет set всех опций с флагами
        Set<Pair<OptionCellular, Boolean>> storage = new HashSet<>();
        storage = getPairSetAllJointlyOptions(option, storage);
        //Преобразует в Set<OptionCellular>
        return castSetAllJointlyOptions(storage);
    }

    private Set<Pair<OptionCellular, Boolean>> getPairSetAllJointlyOptions(OptionCellular optionStart, Set<Pair<OptionCellular, Boolean>> storage) {
        /*
         * Последовательно проходим по всем опциям, забираем у них обязательные для подключения
         * и забрасываем их в общее хранилище
         * */
        if (optionStart.getJointlyOptions().size() != 0) {
            //Записываем все обязательные опции, для стартовой
            for (OptionCellular option : optionStart.getJointlyOptions()) {
                storage.add(new Pair<>(option, false));
            }

            //Вспомогательное хранилище, чтобы не перетирать основное
            Set<Pair<OptionCellular, Boolean>> postStorage = new HashSet<>();
            postStorage.addAll(storage);

            //Проходим по всем опциям в хранилище и рекурсивно достаем из них обязательные опции
            for (Pair<OptionCellular, Boolean> optionPair : postStorage) {
                if (!optionPair.getValue()) {
                    //Меняем флаг опции, т.е. отмечаем, что уже проходили ее
                    //Аналогично: option.flag=true;
                    storage.remove(optionPair);
                    storage.add(new Pair<>(optionPair.getKey(), true));

                    storage = getPairSetAllJointlyOptions(optionPair.getKey(), storage);
                }
            }
        }
        return storage;

    }

    private Set<OptionCellular> castSetAllJointlyOptions(Set<Pair<OptionCellular, Boolean>> inputSet) {
        //Преобразуем set пар в set OptionCellular
        Set<OptionCellular> result = new HashSet<>();
        for (Pair<OptionCellular, Boolean> pair : inputSet) {
            result.add(pair.getKey());
        }
        return result;
    }

    //Получение несовместимых опций
    public Set<OptionCellular> getAllExcludeOptions(OptionCellular option) {
        Set<OptionCellular> rightOptions = option.getExcludeRightOptions();
        Set<OptionCellular> leftOptions = option.getExcludeLeftOptions();

        Set<OptionCellular> allExcludeOptions = new HashSet<>();
        allExcludeOptions.addAll(rightOptions);
        allExcludeOptions.addAll(leftOptions);

        return allExcludeOptions;
    }

    //Отдать список опций с просталенными взаимосвязями для конкретной опции
    //и без самой опции
    public List<OptionCellular> getOptionsWitCorrelation(OptionCellular option) {
        List<OptionCellular> optionsWithCorrelation = this.optionCellularDAO.getList();
        //Исключим саму опцию
        optionsWithCorrelation.remove(option);

        //Проставим зависимости
        for (OptionCellular current : optionsWithCorrelation) {
            current.setCorrelation(typeCorrelationOfMainOnChild(option, current));
        }
        return optionsWithCorrelation;

    }

    //Проставить взаимосвязь между двумя опциями
    public CorrelationType typeCorrelationOfMainOnChild(OptionCellular optionMain, OptionCellular optionChild) {
        Set<OptionCellular> allExcludeOptions = getAllExcludeOptions(optionMain);
        Set<OptionCellular> allJointlyOptions = getAllJointlyOptions(optionMain);

        if (allExcludeOptions.contains(optionChild)) {
            return CorrelationType.EXCLUDE;
        } else {
            if (allJointlyOptions.contains(optionChild)) {
                //Т.е. для подключения optionMain необходимо подключить optionChild
                return CorrelationType.JOINTLY;
            } else {
                return CorrelationType.NONE;
            }
        }
    }

    //Установить взаимосвязь в бд
    public void setCorrelation(Integer idMainOption, Integer idChildOption, String include) throws DataExistenceException {
        //Проверить, какая связь была
        OptionCellular optionMain = this.optionCellularDAO.getById(idMainOption);
        OptionCellular optionChild = this.optionCellularDAO.getById(idChildOption);

        CorrelationType oldCorrelation = typeCorrelationOfMainOnChild(optionMain, optionChild);

        if (!oldCorrelation.getValue().equals(include)) {
            //Связь изменилась
            //Удалим старую связь из бд
            switch (oldCorrelation.getValue()) {
                case "none":
                    break;
                case "jointly":
                    optionMain.getJointlyOptions().remove(optionChild);
                    break;
                case "exclude":
                    if (optionMain.getExcludeLeftOptions().contains(optionChild)) {
                        optionMain.getExcludeLeftOptions().remove(optionChild);
                    } else {
                        optionMain.getExcludeRightOptions().remove(optionChild);
                    }
                    break;
            }
            //Добавим новую
            switch (include) {
                case "none":
                    break;
                case "jointly":
                    optionMain.getJointlyOptions().add(optionChild);
                    break;
                case "exclude":
                    optionMain.getExcludeLeftOptions().add(optionChild);
                    break;
            }
            this.optionCellularDAO.update(optionMain);

        }


    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public OptionCellular getById(Integer id) throws DataExistenceException {
        return (OptionCellular) this.optionCellularDAO.getById(id);
    }

    public void add(OptionCellular optionCellular) {
        this.optionCellularDAO.add(optionCellular);
    }

    public void update(OptionCellular optionCellular) {
        this.optionCellularDAO.update(optionCellular);
    }

    public List<OptionCellular> getList() {
        return this.optionCellularDAO.getList();
    }

    public void remove(Integer id) {
        this.optionCellularDAO.remove(id);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Список всех опций невключенных в тариф или неподключенных как дополнительные с проставленными зависимостями
    //Используется при подключении дополнительных опций к контракту
    public List<OptionCellular> getExtraOptionsListForAdded(Integer idContract) throws DataExistenceException {
        //Все опции в базе
        List<OptionCellular> options = getList();
        //Исключим опции, которые уже есть в контракте как дополнительные и опции, которые уже есть в тарифе
        Contract contract = this.contractService.getById(idContract);
        Set<OptionCellular> extraOptionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsOnTariff = contract.getTariffInContract().getOptionsOnTariff();

        options.removeAll(extraOptionsOnContract);
        options.removeAll(optionsOnTariff);

        //Теперь проставим зависимости для каждой опции
        for (OptionCellular option : options) {
            option.setAllJointlyOptions(getAllJointlyOptions(option));
            option.setAllExcludeOptions(getAllExcludeOptions(option));

            //Проставим флаги: можно ли подключать
            //Если в контракте нет всех необходимых или есть несовместимые - проставим false
            option.setCanBeAdded(checkCanBeOptionAddedOnContract(option, idContract));

        }
        return options;
    }

    public Set<OptionCellular> getExtraOptionsListForDisabled(Integer idContract) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        //Все опции в контракте
        Set<OptionCellular> preOptions = contract.getOptionsOnContract();

        //Вспомогательный
        Set<OptionCellular> options = new HashSet<>();

        //Теперь проставим зависимости для каждой опции
        for (OptionCellular option : preOptions) {

            OptionCellular tempOption = option;

            tempOption.setAllJointlyOptions(getAllJointlyOptions(tempOption));
            tempOption.setAllExcludeOptions(getAllExcludeOptions(tempOption));

            //TODO: Проставим флаги: можно ли отключить
            //tempOption.setCanBeDisabled(checkCanBeOptionDisabledOnContract(tempOption, idContract));
            tempOption.setCanBeDisabled(true);

            options.add(tempOption);

        }

        return options;
    }

    private Boolean checkCanBeOptionAddedOnContract(OptionCellular option, Integer idContract) throws DataExistenceException {
        //Проверим можно ли подключать
        //Если в контракте нет всех необходимых или есть несовместимые - проставим false
        Contract contract = this.contractService.getById(idContract);
        Set<OptionCellular> extraOptionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsOnTariff = contract.getTariffInContract().getOptionsOnTariff();

        Set<OptionCellular> allJointly = getAllJointlyOptions(option);
        Set<OptionCellular> allExclude = getAllExcludeOptions(option);

        if (allJointly.isEmpty() && allExclude.isEmpty()) {
            return true;
        }
        //Проверим, чтобы нигде не были подключены несовместимые
        for (OptionCellular optionExclude : allExclude) {
            if (optionsOnTariff.contains(optionExclude) || extraOptionsOnContract.contains(optionExclude)) {
                return false;
            }
        }
        //Проверим, чтобы были подключены все необходимые
        for (OptionCellular optionJointly : allJointly) {
            if (!optionsOnTariff.contains(optionJointly) && !extraOptionsOnContract.contains(optionJointly)) {
                return false;
            }
        }
        return true;
    }

    private Boolean checkCanBeOptionDisabledOnContract(OptionCellular option, Integer idContract) throws DataExistenceException {
        //Можем отключить, если другие опции в контракте не нуждаются в ней
        Contract contract = this.contractService.getById(idContract);
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        optionsOnContract.remove(option);

        for (OptionCellular optionOnContract : optionsOnContract) {
            if (optionOnContract.getJointlyOptions().contains(option)) {
                return false;
            }
        }

        return true;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Подготовка опций для добавления в корзину клиентом
    public Set<OptionCellular> getJointlyOptionsWithStatus(OptionCellular option, Contract contract) {
        //Список всех необходимых для подключения опций
        Set<OptionCellular> allJointlyForCurrentOption = getAllJointlyOptions(option);

        Set<OptionCellular> allJointly = new HashSet<>();

        for (OptionCellular tempOption : allJointlyForCurrentOption) {
            tempOption.setStatus(checkStatus(option,contract));
            allJointly.add(tempOption);
        }

        return allJointly;
    }

    public Set<OptionCellular> getExcludeOptionsWithStatus(OptionCellular option, Contract contract) {
        //Список всех необходимых для подключения опций
        Set<OptionCellular> allExcludeForCurrentOption = getAllExcludeOptions(option);

        Set<OptionCellular> allExclude = new HashSet<>();

        for (OptionCellular tempOption : allExcludeForCurrentOption) {
            tempOption.setStatus(checkStatus(option,contract));
            allExclude.add(tempOption);
        }

        return allExclude;
    }

    public ExtraOptionStatus checkStatus(OptionCellular option, Contract contract) {
        //Все контейнеры с опциями у контракта
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsOnBasketForAdd = contract.getOptionsForAdd();
        Set<OptionCellular> optionsOnBasketForRemove = contract.getOptionsForRemove();

        if (optionsOnContract.contains(option)) {
            return ExtraOptionStatus.CONNECTED;
        } else {
            if (optionsOnBasketForAdd.contains(option)) {
                return ExtraOptionStatus.IN_BASKET_ON;
            } else {
                if (optionsOnBasketForRemove.contains(option)) {
                    return ExtraOptionStatus.IN_BASKET_OFF;
                } else {
                    return ExtraOptionStatus.NONE;
                }
            }
        }
    }

    public Boolean checkCanBeAddInBasketForAdd(OptionCellular option, Contract contract) {
        //Списки всех необходимых и несовместимых для подключения опций
        Set<OptionCellular> allJointly = getAllJointlyOptions(option);
        Set<OptionCellular> allExclude = getAllExcludeOptions(option);

        //Все контейнеры с опциями у контракта
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsOnBasketForAdd = contract.getOptionsForAdd();
        Set<OptionCellular> optionsOnBasketForRemove = contract.getOptionsForRemove();

        for (OptionCellular tempOption : allJointly) {
            if (!(optionsOnContract.contains(tempOption) || optionsOnBasketForAdd.contains(tempOption))) {
                return false;
            }
        }
        for (OptionCellular tempOption : allExclude) {
            if ((optionsOnContract.contains(tempOption) || optionsOnBasketForAdd.contains(tempOption))) {
                return false;
            }
        }

        return true;
    }

    //Может ли опция быть добавлена на удаление в корзину
    public Boolean checkCanBeAddInBasketForRemove(OptionCellular option, Contract contract) {
        //Списки всех необходимых и несовместимых для подключения опций
        Set<OptionCellular> allJointly = getAllJointlyOptions(option);
        Set<OptionCellular> allExclude = getAllExcludeOptions(option);

        //Все контейнеры с опциями у контракта
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsOnBasketForAdd = contract.getOptionsForAdd();
        Set<OptionCellular> optionsOnBasketForRemove = contract.getOptionsForRemove();

        //Можно удалить, если в контракте или в корзине на подклюсение нет опций, которым она необходима для подключения
        for(OptionCellular tempOption:optionsOnContract){
            if(tempOption.getJointlyOptions().contains(option)){
                return false;
            }
        }
        for(OptionCellular tempOption:optionsOnBasketForAdd){
            if(tempOption.getJointlyOptions().contains(option)){
                return false;
            }
        }
        return true;
    }
}
