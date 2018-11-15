package com.nastinio.spring.service;

import com.nastinio.spring.dao.OptionCellularDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.OptionCellular;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OptionCellularService {
    @Autowired
    OptionCellularDAO optionCellularDAO;

    public OptionCellular getById(Integer id) throws DataExistenceException {
        return (OptionCellular) this.optionCellularDAO.getById(id);
    }

    public void add(OptionCellular optionCellular){
        this.optionCellularDAO.add(optionCellular);
    }

    public void update(OptionCellular optionCellular){
        this.optionCellularDAO.update(optionCellular);
    }

    public List<OptionCellular> getList(){
        return this.optionCellularDAO.getList();
    }

    public void remove(Integer id){
        this.optionCellularDAO.remove(id);
    }

}
