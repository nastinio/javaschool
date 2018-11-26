package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.service.ContractService;
import com.nastinio.spring.service.OptionCellularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("com.nastinio")
public class TempController {
    @Autowired
    OptionCellularService optionCellularService;

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void main() throws DataExistenceException {
    }
}
