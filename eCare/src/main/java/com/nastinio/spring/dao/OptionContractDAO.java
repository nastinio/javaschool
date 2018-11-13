package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.OptionContract;
import com.nastinio.spring.model.OptionContractId;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("tableCouplingDAO")
public class OptionContractDAO extends EntityDAO {
    OptionContractDAO() {
        super(OptionContract.class);
    }

    public OptionContract getById(OptionContractId id) throws DataExistenceException {
        //Session session = this.sessionFactory.getCurrentSession();
        logger.info("Зашли в EntityDAO для поиска по OptionContract id = " + id.toString());
        Session session = this.sessionFactory.openSession();



        OptionContract entity = (OptionContract) session.load(OptionContract.class, id);      //load(T.class, id);
        logger.info(nameClassHeir + " loaded successfully, " + nameClassHeir + " details=" + entity);
        return entity;
    }


    public void deleteExtraOptionByIdContract(Integer idOption, Integer idContract) {
        logger.info("Get for delete ExtraOption #"+idOption+" from contract #"+idContract );
        Session session = this.sessionFactory.openSession();


        //OptionContract entity = new OptionContract(idOption,idContract);
        OptionContract entity = (OptionContract) session.load(OptionContract.class, new OptionContractId(idOption,idContract));
        System.out.println("Получили entity по 2-м id" + entity.getId());
        if (null != entity) {
            session.delete(entity);
        }

       /* String sql = "DELETE FROM ecare.option_contract WHERE id_option='"+idOption+"' and idContract='"+idContract+"'";
        session.createQuery(sql);*/

        logger.info("ExtraOption #"+idOption+" from contract #"+idContract+" deleted successfully." );
    }
}
