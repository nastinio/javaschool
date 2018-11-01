package com.nastinio.spring.service;

import com.nastinio.spring.dao.OptionDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionService {
    private OptionDAO optionDAO;

    public void setOptionDAO(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }


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
            return (Option)this.optionDAO.getById(id);
        } catch (DataExistenceException e) {
            throw e;
        }
    }

    @Transactional
    public void remove(Integer id) {
        this.optionDAO.remove(id);
    }

}
