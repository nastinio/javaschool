package com.nastinio.spring.service;

import com.nastinio.spring.dao.ManagerDAO;
import com.nastinio.spring.dao.PersonDAO;
import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Person;
import org.springframework.stereotype.Service;

@Service
public class SigninService {
    private PersonDAO personDAO;
    private ManagerDAO managerDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    public boolean isRegisteredPerson(Person personOuter){
        Person personInner = (Person) this.personDAO.getById(personOuter.getId());
        if(personInner.getPassword().equals(personOuter.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    public boolean isRegisteredManager(Manager managerOuter){
        Manager managerInner = (Manager) this.managerDAO.getById(managerOuter.getId());
        if(managerInner.getPassword().equals(managerOuter.getPassword())){
            return true;
        }else{
            return false;
        }
    }



}
