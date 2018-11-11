/*
package com.nastinio.spring.dao;

import com.nastinio.spring.model.OptionContract;
import com.nastinio.spring.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("tableCouplingDAO")
public class OptionContractDAO extends EntityDAO {
    OptionContractDAO() {
        super(OptionContract.class);
    }

    public void deleteExtraOptionByIdContract(Integer idOption, Integer idContract) {
        Session session = this.sessionFactory.openSession();

        OptionContract entity = new OptionContract(idOption,idContract);
        if (null != entity) {
            session.delete(entity);
        }

        */
/*String sql = "DELETE FROM ecare.option_contract WHERE id_option='"+idOption+"' and idContract='"+idContract+"'";
        session.createQuery(sql);*//*

        logger.info("ExtraOption #"+idOption+" from contract #"+idContract+" deleted successfully." );
    }
}
*/
