package com.nastinio.spring.dao;

import com.nastinio.spring.model.EntityModel;
import com.nastinio.spring.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.logging.LogManager;

public class EntityDAO implements Crud<Integer, EntityModel> {

    private static final Logger logger = LoggerFactory.getLogger(EntityDAO.class);   // LoggerFactory.getLogger(PersonDAO.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    private String nameClass = EntityDAO.class.getName();

    @Override
    public void add(EntityModel entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entity);
        logger.info(nameClass + " saved successfully. " + nameClass + "  details=" + entity);
    }

    @Override
    public void update(EntityModel entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        logger.info(nameClass + " updated successfully. " + nameClass + "  details=" + entity);
    }

    @Override
    public List<EntityModel> getList() {
        /*Session session = this.sessionFactory.getCurrentSession();
        List<EntityModel> entitiesList = session.createQuery("from Person").list();
        for (EntityModel currentEntity : entitiesList) {
            logger.info("List::" + currentEntity);
        }
        return entitiesList;*/
        return null;
    }

    @Override
    public EntityModel getById(Integer id) {
        //Session session = this.sessionFactory.getCurrentSession();
        Session session = this.sessionFactory.openSession();
        EntityModel entity = (EntityModel) session.load(EntityModel.class, id);
        logger.info("Person loaded successfully, Person details=" + entity);
        return entity;
    }

    @Override
    public void remove(Integer id) {
        /*Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details=" + p);*/
    }
}
