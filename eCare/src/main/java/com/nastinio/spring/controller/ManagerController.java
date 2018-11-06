package com.nastinio.spring.controller;

import com.nastinio.spring.model.Manager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("person", new Person());
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
