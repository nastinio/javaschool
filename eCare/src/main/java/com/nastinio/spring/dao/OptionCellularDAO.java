package com.nastinio.spring.dao;

import com.nastinio.spring.model.OptionCellular;
import org.springframework.stereotype.Repository;

@Repository("optionCellularDAO")
public class OptionCellularDAO extends EntityDAO<OptionCellular> {
    OptionCellularDAO(){
        super(OptionCellular.class);
    }

}
