package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nastinio.spring.model.Person;

@Repository("personDAO")
public class PersonDAO extends EntityDAO {

    PersonDAO() {
        super(Person.class);
    }


    /*public void doOnline(Integer id) throws DataExistenceException {
        Person person = (Person)getById(id);
        person.setOnline(true);
        logger.info("Get for update online-status: " + person.toString());
        update(person);
    }

    public void doOffline(Integer id) throws DataExistenceException {
        Person person = (Person)getById(id);
        person.setOnline(false);
        logger.info("Get for update online-status: " + person.toString());
        update(person);
    }*/

}
