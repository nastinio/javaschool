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


    public void doOnline(Integer id) throws DataExistenceException {
        //Session session = this.sessionFactory.getCurrentSession();
        //Session session = super.sessionFactory.openSession();
        Person person = (Person)getById(id);
        person.setOnline(true);
        logger.info("Get for update online-status: " + person.toString());
        update(person);

        /*System.out.println("Получили на обновление: " + person.toString());
        //session.createQuery("UPDATE `ecare`.`person` SET `isOnline`='1' WHERE `id`='"+person.getId()+"';");
        session.update(person);
        logger.info(nameClassHeir + " updated successfully. " + nameClassHeir + "  details=" + person);
        session.close();*/
    }

}
