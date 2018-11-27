package com.nastinio.spring.service;

import com.nastinio.spring.dao.ContractDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("contractService")
@Transactional
public class ContractService {

    @Autowired
    ContractDAO contractDAO;

    @Autowired
    PersonService personService;

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionCellularService optionCellularService;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Блокировка/разблокировка
    public void blockContractByPerson(Integer idContract) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        contract.setIsBlockedByPerson(1);
        this.contractDAO.update(contract);
    }

    public void unlockContractByPerson(Integer idContract) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        contract.setIsBlockedByPerson(0);
        this.contractDAO.update(contract);
    }

    public void blockContractByManager(Integer idContract) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        contract.setIsBlockedByManager(1);
        this.contractDAO.update(contract);
    }

    public void unlockContractByManager(Integer idContract) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        contract.setIsBlockedByManager(0);
        this.contractDAO.update(contract);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Contract getById(Integer id) throws DataExistenceException {
        return (Contract) this.contractDAO.getById(id);
    }

    public void add(Contract contract) {
        this.contractDAO.add(contract);
    }

    public void update(Contract contract) {
        this.contractDAO.update(contract);
    }

    public void merge(Contract contract) {
        this.contractDAO.merge(contract);
    }

    public List<Contract> getList() {
        return this.contractDAO.getList();
    }

    public void remove(Integer id) {
        this.contractDAO.remove(id);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Поиск контракта по номеру
    public List<Contract> getSearchList(String target) {
        return this.contractDAO.searchContract(target);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Удаление/добавление опций в контракт менеджером
    public void removeExtraOptionFromContract(Integer idContract, Integer idOption) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        Set<OptionCellular> optionSet = contract.getOptionsOnContract();

        OptionCellular optionForRemove = new OptionCellular();

        for (OptionCellular option : optionSet) {
            if (option.getId() == idOption) {
                optionForRemove = option;
                break;
            }
        }

        if (optionForRemove.getId() != null) {
            optionSet.remove(optionForRemove);
            this.update(contract);
        } else {
            //TODO: пробросить исключение
        }

    }

    public void addForPerson(Contract contract, Integer idPerson) throws DataExistenceException {
        Person person = this.personService.getById(idPerson);
        contract.setPersonInContract(person);

        Tariff tariff = this.tariffService.getById(contract.getIdTariff());
        contract.setTariffInContract(tariff);

        this.contractDAO.add(contract);
    }

    public void updateWithTariff(Contract contract, Integer idPerson) throws DataExistenceException {
        //Contract contract = (Contract) this.contractDAO.getById(idContract);
        Person person = this.personService.getById(idPerson);
        contract.setPersonInContract(person);

        Tariff tariff = this.tariffService.getById(contract.getIdTariff());
        contract.setTariffInContract(tariff);

        this.contractDAO.update(contract);
    }

    //Проставить зависимости в дополнительных опциях контракта
    public Contract getContractWithDependenciesOnOptions(Integer idContract) throws DataExistenceException {
        Contract contract = getById(idContract);
        for (OptionCellular option : contract.getOptionsOnContract()) {
            option.setAllJointlyOptions(this.optionCellularService.getAllJointlyOptions(option));
            option.setAllJointlyOptions(this.optionCellularService.getAllExcludeOptions(option));
        }
        return contract;

    }

    public void addExtraOptionToContract(Integer idContract, Integer idOption) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        Set<OptionCellular> options = contract.getOptionsOnContract();
        options.add(option);
        contract.setOptionsOnContract(options);

        this.contractDAO.update(contract);

    }

    public void disableExtraOptionToContract(Integer idContract, Integer idOption) throws DataExistenceException {
        Contract contract = getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        contract.getOptionsOnContract().remove(option);

        this.contractDAO.update(contract);
    }

    public void updateTariffByPerson(Integer idContract) throws DataExistenceException {
        Contract contract = this.getById(idContract);
        if (contract.getTariffInContractForChange() == null || contract.getTariffInContractForChange().getId() == contract.getTariffInContract().getId()) {
            return;
        }
        contract.setTariffInContract(contract.getTariffInContractForChange());
        this.update(contract);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Сброс корзины
    public void resetTariffChange(Integer idContract) throws DataExistenceException {
        Contract contract = this.getById(idContract);
        if (contract.getTariffInContractForChange() == null || contract.getTariffInContractForChange().getId() == contract.getTariffInContract().getId()) {
            return;
        } else {
            contract.setTariffInContractForChange(contract.getTariffInContract());
            this.update(contract);
        }
    }

    public void resetOptionForAdd(Integer idContract, Integer idOption) throws DataExistenceException {
        Contract contract = this.getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        Set<OptionCellular> optionsForAdd = contract.getOptionsForAdd();
        optionsForAdd.remove(option);
        contract.setOptionsForAdd(optionsForAdd);

        this.update(contract);

    }

    public void resetOptionForRemove(Integer idContract, Integer idOption)throws DataExistenceException  {
        Contract contract = this.getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        Set<OptionCellular> optionsForRemove = contract.getOptionsForRemove();
        optionsForRemove.remove(option);
        contract.setOptionsForRemove(optionsForRemove);

        this.update(contract);
    }

    public void resetBasketAll(Integer idContract)throws DataExistenceException  {
        Contract contract = this.getById(idContract);
        resetTariffChange(idContract);
        contract.setOptionsForAdd(new HashSet<OptionCellular>());
        contract.setOptionsForRemove(new HashSet<OptionCellular>());
        this.update(contract);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Добавим в корзину, чтобы подключить
    public void addOptionInBasket(Contract contract, OptionCellular option) {
        Set<OptionCellular> optionsForAdd = contract.getOptionsForAdd();
        optionsForAdd.add(option);
        contract.setOptionsForAdd(optionsForAdd);

        this.contractDAO.update(contract);
    }

    //Добавим в корзину, чтобы отключить
    public void addOptionInBasketForRemove(Contract contract, OptionCellular option) {
        Set<OptionCellular> optionsForRemove = contract.getOptionsForRemove();
        optionsForRemove.add(option);
        contract.setOptionsForRemove(optionsForRemove);

        this.contractDAO.update(contract);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Проставим CanBeDisabledByPerson для отображения кнопки 'Отключить' в контракте пользователем
    public Object getOptionsOnContractWithDisableStatus(Integer idContract) throws DataExistenceException {
        Contract contract = this.getById(idContract);

        Set<OptionCellular> options = new HashSet<>();

        for(OptionCellular option:contract.getOptionsOnContract()){
            option.setCanBeDisabledByPerson(this.optionCellularService.checkCanBeAddInBasketForRemove(option, contract));
            //option.setStatus(this.optionCellularService.checkStatus(option, contract));
            options.add(option);
        }

        return options;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Проверяет все содержимое корзины на возможность переноса в базу
    public Boolean checkValidityChangesFromBasket(Contract contract) {
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsForAdd = contract.getOptionsForAdd();
        Set<OptionCellular> optionsForRemove = contract.getOptionsForRemove();

        //Проверим опции для добавления
        //Все необходимые для их подключения опции должны быть либо подключены, либо лежать в корзине для добавления
        //И не должны находиться в опциях на удаление
        for(OptionCellular option:optionsForAdd){
            for(OptionCellular joinOption:option.getJointlyOptions()){
                if(!((optionsOnContract.contains(joinOption) || optionsForAdd.contains(joinOption)) && !optionsForRemove.contains(joinOption)) ){
                    return false;
                }
            }
        }

        //Несовместимые
        //Опции в контракте или на добавление не содержат несовместимые опции
        Set<OptionCellular> optionsOnContractWithoutOptionsForRemove = optionsOnContract;
        optionsOnContractWithoutOptionsForRemove.removeAll(optionsForRemove);

        for(OptionCellular option:optionsForAdd){
            for(OptionCellular excludeOption:option.getExcludeLeftOptions()){
                if((optionsForAdd.contains(excludeOption)) || optionsOnContractWithoutOptionsForRemove.contains(excludeOption) ){
                    return false;
                }
            }
        }

        //Все опции в контракте, за исключением удаляемых, имеют необходимые им опциии либо в подключаемых, либо в самом контракте
        for(OptionCellular option:optionsOnContractWithoutOptionsForRemove){
            for(OptionCellular joinOption:option.getJointlyOptions()){
                if(!((optionsOnContract.contains(joinOption) || optionsForAdd.contains(joinOption)) && !optionsForRemove.contains(joinOption)) ){
                    return false;
                }
            }
        }

        return true;
    }

    //Перекидываем все опции их корзины в контракт
    public void moveOptionsFromBasketToContract(Contract contract) {
        Set<OptionCellular> optionsOnContract = contract.getOptionsOnContract();
        Set<OptionCellular> optionsForAdd = contract.getOptionsForAdd();
        Set<OptionCellular> optionsForRemove = contract.getOptionsForRemove();

        optionsOnContract.removeAll(optionsForRemove);
        optionsOnContract.addAll(optionsForAdd);

        contract.setOptionsForAdd(new HashSet<OptionCellular>());
        contract.setOptionsForRemove(new HashSet<OptionCellular>());

        this.update(contract);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
