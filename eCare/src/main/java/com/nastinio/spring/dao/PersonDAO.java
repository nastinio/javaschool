package com.nastinio.spring.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nastinio.spring.model.Person;

@Repository
public class PersonDAO extends EntityDAO {

    PersonDAO() {
        super(Person.class);
    }




    /*private static final Logger logger = LoggerFactory.getLogger(PersonDAO.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }*/

    /*public void addPerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Person saved successfully, Person Details=" + p);
    }

    public void updatePerson(Person p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Person updated successfully, Person Details=" + p);
    }
    public List<Person> listPersons() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personsList = session.createQuery("from Person").list();
        for (Person p : personsList) {
            logger.info("Person List::" + p);
        }
        return personsList;
    }

    public Person getPersonById(String id) {
        //Session session = this.sessionFactory.getCurrentSession();
        Session session = this.sessionFactory.openSession();
        Person p = (Person) session.load(Person.class, id);
        logger.info("Person loaded successfully, Person details=" + p);
        return p;
    }

    public void removePerson(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new String(id));
        if (null != p) {
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details=" + p);
    }*/
}
