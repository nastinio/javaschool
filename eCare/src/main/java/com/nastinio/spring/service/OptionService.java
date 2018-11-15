/*
package com.nastinio.spring.service;

import com.nastinio.spring.dao.OptionDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OptionService {

    @Autowired
    OptionDAO optionDAO;

    */
/*@Autowired
    OptionsJointlyDAO optionsJointlyDAO;*//*



    @Transactional
    public void add(Option option) {
        this.optionDAO.add(option);
    }


    @Transactional
    public void update(Option p) {
        this.optionDAO.update(p);
    }

    @Transactional
    public List<Option> list() {
        return this.optionDAO.getList();
    }

    @Transactional
    public Option getById(Integer id) throws DataExistenceException {
        try {
            return (Option) this.optionDAO.getById(id);
        } catch (DataExistenceException e) {
            throw e;
        }
    }

    @Transactional
    public void remove(Integer id) {
        this.optionDAO.remove(id);
    }

    @Transactional
    public Set<Option> getJointlyOptionsSet(Integer id) {
        return this.optionDAO.getJointlyOptionsSet(id);
    }

    @Transactional
    public Set<Option> getExcludeOptionsSet(Integer id) {
        return this.optionDAO.getExcludeOptionsSet(id);
    }

    @Transactional
    public Set<Option> getOptionsWithoutRulesSet(Integer id) {
        Set<Option> optionJointlySet = getJointlyOptionsSet(id);
        Set<Option> optionExcludeSet = getExcludeOptionsSet(id);
        List<Option> optionAllList = list();

        Set<Option> optionsWithRulesSet = new HashSet<>();

        for (Option currentOption : optionAllList) {
            boolean hasRule = false;
            for (Option joinOption : optionJointlySet) {
                if (joinOption.id == currentOption.id) {
                    currentOption.setRule(OptionRules.JOINTLY);
                    //optionsWithRulesSet.add(currentOption);
                    hasRule = true;
                    break;
                }
            }
            for (Option excludeOption : optionExcludeSet) {
                if (excludeOption.id == currentOption.id) {
                    currentOption.setRule(OptionRules.EXCLUDE);
                    //optionsWithRulesSet.add(currentOption);
                    hasRule = true;
                    break;
                }
            }
            if (!hasRule && currentOption.id != id) {
                currentOption.setRule(OptionRules.STANDART);
                optionsWithRulesSet.add(currentOption);
            }


        }

        */
/*for (Option temp : optionsWithRulesSet) {
            System.out.println(temp.id + " " + temp.getName() + " " + temp.getRule());
        }*//*


        return optionsWithRulesSet;
    }


}
*/
