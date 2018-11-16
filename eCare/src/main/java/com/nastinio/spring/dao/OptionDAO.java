/*
package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("optionDAO")
public class OptionDAO extends EntityDAO<Option> {
    OptionDAO() {
        super(Option.class);
    }

    public Set<Option> getJointlyOptionsSet(Integer id) {
        //Session session = this.sessionFactory.getCurrentSession();
        try {
            Option option = getById(id);
            Set<Option> jointlyOptionsSet = option.getJointlyOptions();
            return jointlyOptionsSet;
        } catch (DataExistenceException e) {
           super.logger.info("Option with id = " + id + " doesn't exist");
           // TODO REturn null is bad))


           return null;
        }
    }

    public Set<Option> getExcludeOptionsSet(Integer id) {
        //Session session = this.sessionFactory.getCurrentSession();
        try {
            Option option = getById(id);
            Set<Option> excludeOptionsSet = option.getExcludeLeftOptions();
            return excludeOptionsSet;
        } catch (DataExistenceException e) {
            super.logger.info("Option with id = " + id + " doesn't exist");
            return null;
        }
    }


}
*/
