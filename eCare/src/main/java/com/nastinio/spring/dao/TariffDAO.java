package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import com.nastinio.spring.model.Tariff;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("tariffDAO")
public class TariffDAO extends EntityDAO<Tariff> {
    TariffDAO() {
        super(Tariff.class);
    }

    public Set<Option> getOptionSet(Integer id) throws DataExistenceException {
        //Возвращает set опций для одного тарифа
        Tariff tariff = getById(id);
        Set<Option> optionsSet = tariff.getOptionSet();
        return optionsSet;

    }
}
