package com.nastinio.spring.dao;

import com.nastinio.spring.model.Contract;

import java.util.List;

public interface ContractDAO {
    public void addContract(Contract contract);
    public void updateContract(Contract contract);
    public List<Contract> listContracts();
    public Contract getContractById(String id);
    public void removeContract(String id);
}
