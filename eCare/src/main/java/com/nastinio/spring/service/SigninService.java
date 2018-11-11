package com.nastinio.spring.service;

import com.nastinio.spring.dao.ManagerDAO;
import com.nastinio.spring.dao.PersonDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SigninService {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    ManagerDAO managerDAO;

   /* public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }*/

    public boolean isRegisteredPerson(Person personOuter){
        try{
            Person personInner = (Person) this.personDAO.getById(personOuter.getId());
            if(personInner.getPassword().equals(personOuter.getPassword())){
                return true;
            } else{
                return false;
            }
        }catch (DataExistenceException e){
            return false;
        }
    }

    @Transactional
    public void doOnline(Integer id) throws DataExistenceException {
        this.personDAO.doOnline(id);
    }

    @Transactional
    public void doOffline(Integer id) throws DataExistenceException {
        this.personDAO.doOffline(id);
    }


    public boolean isRegisteredManager(Manager managerOuter){
        try{
            Manager managerInner = (Manager) this.managerDAO.getById(managerOuter.getId());
            if(managerInner.getPassword().equals(managerOuter.getPassword())){
                return true;
            }else{
                return false;
            }
        }catch (DataExistenceException e){
            return false;
        }

    }

    public Person getPersonById(Integer id) throws DataExistenceException {
        try{
            return (Person) this.personDAO.getById(id);
        }catch (DataExistenceException e){
            throw e;
        }

    }



}
