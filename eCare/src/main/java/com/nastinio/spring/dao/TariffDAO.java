package com.nastinio.spring.dao;

import com.nastinio.spring.model.Tariff;
import org.springframework.stereotype.Repository;

@Repository("tariffDAO")
public class TariffDAO extends EntityDAO<Tariff> {
    TariffDAO(){
        super(Tariff.class);
    }
}
