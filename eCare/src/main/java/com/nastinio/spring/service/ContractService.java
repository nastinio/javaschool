package com.nastinio.spring.service;

import com.nastinio.spring.dao.ContractDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.OptionCellular;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("contractService")
@Transactional
public class ContractService {

    @Autowired
    ContractDAO contractDAO;

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

    public Contract getById(Integer id) throws DataExistenceException {
        return (Contract) this.contractDAO.getById(id);
    }

    public void add(Contract contract){
        this.contractDAO.add(contract);
    }

    public void update(Contract contract){
        this.contractDAO.update(contract);
    }

    public List<Contract> getList(){
        return this.contractDAO.getList();
    }

    public void remove(Integer id){
        this.contractDAO.remove(id);
    }

    public void removeExtraOptionFromContract(Integer idContract, Integer idOption) throws DataExistenceException {
        Contract contract = (Contract) this.contractDAO.getById(idContract);
        Set<OptionCellular> optionSet = contract.getOptionsOnContract();

        OptionCellular optionForRemove = new OptionCellular();

        for(OptionCellular option: optionSet){
            if(option.getId()==idOption){
                optionForRemove = option;
                break;
            }
        }

        if(optionForRemove.getId()!=null){
            optionSet.remove(optionForRemove);
            this.update(contract);
        }else{
            //TODO: пробросить исключение
        }

    }
}
