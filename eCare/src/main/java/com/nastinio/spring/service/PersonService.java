package com.nastinio.spring.service;

import com.nastinio.spring.dao.PersonDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Transactional
    public void addPerson(Person p) {
        this.personDAO.add(p);
    }

    @Transactional
    public void updatePerson(Person p) {
        this.personDAO.update(p);
    }

    @Transactional
    public List<Person> listPersons() {
        return this.personDAO.getList();
    }

    @Transactional
    public Person getPersonById(Integer id) throws DataExistenceException {
        try {
            return (Person)this.personDAO.getById(id);
        } catch (DataExistenceException e) {
            throw e;
        }
    }

    @Transactional
    public void removePerson(Integer id) {
        this.personDAO.remove(id);
    }

}
