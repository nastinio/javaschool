package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class EntityDAO<T> implements Crud<T> {

    /*
     * Абстрактный класс, реализующий все методы интерфейса Crud
     * Все классы DAO наследуются от него
     *
     * */

    @Autowired
    protected SessionFactory sessionFactory;
    /*public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }*/

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    private Class nameClass;
    protected static Logger logger;// = LoggerFactory.getLogger(EntityDAO.class);   // LoggerFactory.getLogger(PersonDAO.class);


    protected String nameClassHeir;

    EntityDAO(Class nameClass) {
        this.nameClass = nameClass;
        this.nameClassHeir = nameClass.getSimpleName();

        logger = LoggerFactory.getLogger(nameClass);
    }


    @Override
    public void add(T entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entity);
        logger.info(nameClassHeir + " saved successfully. " + nameClassHeir + "  details=" + entity);
    }

    @Override
    public void update(T entity) {
        System.out.println("Получили на обновление " + entity.toString());
        Session session = this.sessionFactory.getCurrentSession();
        //Session session = this.sessionFactory.openSession();
        //session.flush();
        session.update(entity);
        logger.info(nameClassHeir + " updated successfully. " + nameClassHeir + "  details=" + entity);

    }

    public void merge(T entity) {
        System.out.println("Получили на обновление " + entity.toString());
        Session session = this.sessionFactory.getCurrentSession();
        //Session session = this.sessionFactory.openSession();
        //session.flush();
        session.merge(entity);
        logger.info(nameClassHeir + " merged successfully. " + nameClassHeir + "  details=" + entity);

    }

   /* public void save(T entity){
        System.out.println("Получили на обновление " + entity.toString());
        Session session = this.sessionFactory.getCurrentSession();
        //Session session = this.sessionFactory.openSession();
        session.flush();
        session.update(entity);
        logger.info(nameClassHeir + " saved successfully. " + nameClassHeir + "  details=" + entity);
    }*/

    @Override
    public List<T> getList() {
        Session session = this.sessionFactory.getCurrentSession();
        //Session session = this.sessionFactory.openSession();
        logger.info("Try get list all " + nameClassHeir);
        List<T> entitiesList = session.createQuery("from " + nameClassHeir).list();//session.createQuery("from Person").list();
        logger.info("CreateQuery success");
        for (T currentEntity : entitiesList) {
            logger.info("List::" + currentEntity);
        }
        return entitiesList;
        //return null;
    }

    //@Override
    public T getById(Integer id) throws DataExistenceException {
        Session session = this.sessionFactory.getCurrentSession();
        logger.info("Зашли в EntityDAO для поиска по id = " + id);
        //Session session = this.sessionFactory.openSession();
        try {
            T entity = (T) session.load(nameClass, id);      //load(T.class, id);
            logger.info(nameClassHeir + " loaded successfully, " + nameClassHeir + " details=" + entity.toString());
            return entity;
        } catch (ObjectNotFoundException e) {
            logger.info("Person #" + id + "doesn't exist");
            throw new DataExistenceException();
        } /*finally {
            session.close();
        }*/

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


    public List<Contract> searchContract(String target) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Contract> entitiesList = session.createSQLQuery("select * from Contract where number LIKE '%" + target + "%'").addEntity(Contract.class).list();
        logger.info("CreateSQLQuery success");
        for (Contract currentEntity : entitiesList) {
            logger.info("List::" + currentEntity.getNumber());
        }
        return entitiesList;

    }



    /*//Костыль
    public void addExtraOption(Integer idContract,Integer idOption) {
        Session session = this.sessionFactory.getCurrentSession();
        session.createSQLQuery("INSERT INTO `ecaredb`.`contract_option` (`id_contract`, `id_option`) VALUES ('"+idContract+"', '"+idOption+"')");
        logger.info("CreateSQLQuery success");


    }*/


}
