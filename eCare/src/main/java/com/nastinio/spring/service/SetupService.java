package com.nastinio.spring.service;

import com.nastinio.spring.dao.PersonDAO;
import com.nastinio.spring.model.Person;
import org.springframework.stereotype.Service;

@Service
public class SetupService {
    private PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean isRegisteredPerson(Person personOuter){
        Person personInner = this.personDAO.getPersonById(personOuter.getId());
        if(personInner.getPassword().equals(personOuter.getPassword())){
            return true;
        }else{
            return false;
        }

    }



}
