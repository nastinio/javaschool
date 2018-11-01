package com.nastinio.spring.dao;

import com.nastinio.spring.model.Manager;
import org.springframework.stereotype.Repository;

@Repository("managerDAO")
public class ManagerDAO extends EntityDAO<Manager>{
    ManagerDAO(){
        super(Manager.class);
    }
}
