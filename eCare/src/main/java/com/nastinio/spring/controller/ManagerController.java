package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Option;
import com.nastinio.spring.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@ComponentScan("com.nastinio")
public class ManagerController {
    @RequestMapping(value = "/ecareManager", method = RequestMethod.GET)
    public ModelAndView ecareManagerStart(@ModelAttribute("manager")Manager manager) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("manager", manager);
        modelAndView.setViewName("manager/ecareManager");
        return modelAndView;
    }

}
