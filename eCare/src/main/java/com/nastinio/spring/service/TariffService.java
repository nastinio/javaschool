package com.nastinio.spring.service;

import com.nastinio.spring.dao.TariffDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Service("tariffService")
public class TariffService {
    @Autowired
    TariffDAO tariffDAO;

    public Tariff getById(Integer id) throws DataExistenceException {
        return this.tariffDAO.getById(id);
    }

    public void add(Tariff tariff) {
        this.tariffDAO.add(tariff);
    }

    public void update(Tariff tariff) {
        this.tariffDAO.update(tariff);
    }

    public List<Tariff> getList() {
        return this.tariffDAO.getList();
    }

    public void remove(Integer id) {
        this.tariffDAO.remove(id);
    }



    ////////////////////////////////////////
    /*@Transactional
    public List<Tariff> getTariffList(){
        return tariffDAO.getList();
    }

    @Transactional
    public Set<Option> getOptionsSet(Integer id) throws DataExistenceException {
        return this.tariffDAO.getOptionSet(id);
    }*/



}
