package com.nastinio.spring.dao;

import com.nastinio.spring.model.Option;
import org.springframework.stereotype.Repository;

@Repository("optionDAO")
public class OptionDAO extends EntityDAO<Option> {
    OptionDAO(){
        super(Option.class);
    }
}
