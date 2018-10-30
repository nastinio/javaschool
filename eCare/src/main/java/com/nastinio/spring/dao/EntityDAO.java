package com.nastinio.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class EntityDAO<T> implements Crud<T> {

    /*
    * Абстрактный класс, реализующий все методы интерфейса Crud
    * Все классы DAO наследуются от него
    *
    * */

    private Class nameClass;
    protected static  Logger logger;// = LoggerFactory.getLogger(EntityDAO.class);   // LoggerFactory.getLogger(PersonDAO.class);
    protected SessionFactory sessionFactory;


    EntityDAO(Class nameClass) {
        this.nameClass = nameClass;
        logger = LoggerFactory.getLogger(nameClass);
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void add(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entity);
        logger.info(nameClass + " saved successfully. " + nameClass + "  details=" + entity);
    }

    @Override
    public void update(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
        logger.info(nameClass + " updated successfully. " + nameClass + "  details=" + entity);
    }

    @Override
    public List<T> getList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<T> entitiesList = session.createQuery("from " + nameClass).list();   //session.createQuery("from Person").list();
        for (T currentEntity : entitiesList) {
            logger.info("List::" + currentEntity);
        }
        return entitiesList;
        //return null;
    }

    //@Override
    public T getById(Integer id) {
        //Session session = this.sessionFactory.getCurrentSession();
        logger.info("Зашли в EntityDAO для поиска по id = " + id);
        Session session = this.sessionFactory.openSession();
        T entity = (T) session.load(nameClass, id);      //load(T.class, id);
        logger.info(nameClass + " loaded successfully, " + nameClass + " details=" + entity);
        session.close();
        return entity;


    }

    @Override
    public void remove(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        T entity = (T) session.load(nameClass, id);
        if (null != entity) {
            session.delete(entity);
        }
        logger.info(nameClass + " deleted successfully. " + nameClass + "  details=" + entity);
    }


}
