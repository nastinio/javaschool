package com.nastinio.spring.service;

import com.nastinio.spring.dao.PersonDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personService")
@Transactional
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    public void add(Person p) {
        this.personDAO.add(p);
    }


    public void update(Person p) {
        this.personDAO.update(p);
    }


    public List<Person> getList() {
        return this.personDAO.getList();
    }


    public Person getById(Integer id) throws DataExistenceException {
        try {
            return (Person)this.personDAO.getById(id);
        } catch (DataExistenceException e) {
            throw e;
        }
    }


    public void remove(Integer id) {
        this.personDAO.remove(id);
    }

}
