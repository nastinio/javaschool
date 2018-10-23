package com.nastinio.spring.dao;

import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractDAOImpl implements ContractDAO {

    private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addContract(Contract contract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(contract);
        logger.info("Contract saved successfully, Contract Details=" + contract);
    }

    @Override
    public void updateContract(Contract contract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(contract);
        logger.info("Person updated successfully, Person Details=" + contract);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contract> listContracts(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Contract> contractsList = session.createQuery("from Contract").list();
        for (Contract contract : contractsList) {
            logger.info("Contract List::" + contract);
        }
        return contractsList;
    }

    @Override
    public Contract getContractById(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contract contract = (Contract) session.load(Contract.class, new String(id));
        logger.info("Contract loaded successfully, Contract details=" + contract);
        return contract;
    }

    @Override
    public void removeContract(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person contract = (Person) session.load(Person.class, new Integer(id));
        if (null != contract) {
            session.delete(contract);
        }
        logger.info("Person deleted successfully, person details=" + contract);
    }

}
