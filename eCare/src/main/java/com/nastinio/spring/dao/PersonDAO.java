package com.nastinio.spring.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nastinio.spring.model.Person;

@Repository("personDAO")
public class PersonDAO extends EntityDAO {

    PersonDAO() {
        super(Person.class);
    }

}
