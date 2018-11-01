package com.nastinio.spring.dao;

import com.nastinio.spring.model.Contract;
import org.springframework.stereotype.Repository;


@Repository("contractDAO")
public class ContractDAO extends EntityDAO {

    ContractDAO() {
        super(Contract.class);
    }
}