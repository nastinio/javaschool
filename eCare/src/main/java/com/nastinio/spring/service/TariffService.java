package com.nastinio.spring.service;

import com.nastinio.spring.dao.TariffDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("tariffService")
public class TariffService {
    @Autowired
    TariffDAO tariffDAO;

    @Transactional
    public List<Tariff> getTariffList(){
        return tariffDAO.getList();
    }

    @Transactional
    public Set<Option> getOptionsSet(Integer id) throws DataExistenceException {
        return this.tariffDAO.getOptionSet(id);
    }



}
