package com.nastinio.spring.service;

import com.nastinio.spring.dao.OptionCellularDAO;
import com.nastinio.spring.enums.CorrelationType;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.OptionCellular;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OptionCellularService {
    @Autowired
    OptionCellularDAO optionCellularDAO;

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
    public Set<OptionCellular> getAllExcludeOptions(OptionCellular option){
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
        for(OptionCellular current:optionsWithCorrelation){
            current.setCorrelation(typeCorrelationOfMainOnChild(option,current));
        }
        return optionsWithCorrelation;

    }

    //Проставить взаимосвязь между двумя опциями
    public CorrelationType typeCorrelationOfMainOnChild(OptionCellular optionMain,OptionCellular optionChild){
        Set<OptionCellular> allExcludeOptions = getAllExcludeOptions(optionMain);
        Set<OptionCellular> allJointlyOptions = getAllJointlyOptions(optionMain);

        if(allExcludeOptions.contains(optionChild)){
           return CorrelationType.EXCLUDE;
        }else{
            if(allJointlyOptions.contains(optionChild)){
                //Т.е. для подключения optionMain необходимо подключить optionChild
               return CorrelationType.JOINTLY;
            }else{
                return CorrelationType.NONE;
            }
        }
    }
    //Установить взаимосвязь в бд
    public void setCorrelation(Integer idMainOption, Integer idChildOption, String include) throws DataExistenceException {
        //Проверить, какая связь была
        OptionCellular optionMain = this.optionCellularDAO.getById(idMainOption);
        OptionCellular optionChild = this.optionCellularDAO.getById(idChildOption);

        CorrelationType oldCorrelation = typeCorrelationOfMainOnChild(optionMain,optionChild);

        if(!oldCorrelation.getValue().equals(include)){
            //Связь изменилась
            //Удалим старую связь из бд
            switch (oldCorrelation.getValue()){
                case "none":
                    break;
                case "jointly":
                    optionMain.getJointlyOptions().remove(optionChild);
                    break;
                case "exclude":
                    if(optionMain.getExcludeLeftOptions().contains(optionChild)){
                        optionMain.getExcludeLeftOptions().remove(optionChild);
                    }else{
                        optionMain.getExcludeRightOptions().remove(optionChild);
                    }
                    break;
            }
            //Добавим новую
            switch (include){
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

}
