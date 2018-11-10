package com.nastinio.spring.service;

import com.nastinio.spring.dao.ContractDAO;
import com.nastinio.spring.dao.TariffDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

}
