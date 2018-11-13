package com.nastinio.spring.controller;


import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.service.PersonService;
import com.nastinio.spring.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nastinio.spring.model.Person;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@ComponentScan("com.nastinio")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    TariffService tariffService;

    /*@Autowired(required = true)
    @Qualifier(value = "optionService")
    public void setPersonService(PersonService ps) {
        this.optionService = ps;
    }*/

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person/person";
    }

    //For add and update person both
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p) {

        // TODO NPE!!
        if (p.getId() == null) {
            //new person, add it
            this.personService.addPerson(p);
        } else {
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/persons";

    }

    @RequestMapping("/person/remove/{id}")
    public String removePerson(@PathVariable("id") Integer id) {

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/person/edit/{id}")
    public ModelAndView editPerson(@PathVariable("id") Integer id, Model model) throws DataExistenceException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("person", this.personService.getPersonById(id));
        modelAndView.addObject("listPersons", this.personService.listPersons());
        modelAndView.setViewName("person/person");
        return modelAndView;

        //return "redirect:/persons";


    }


    @RequestMapping("/person/${id}")
    public ModelAndView showPerson(@PathVariable("id") Integer id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("person", this.personService.getPersonById(id));
            modelAndView.setViewName("person/personEdit");
            return modelAndView;
        } catch (DataExistenceException e) {
            return null;
        }
    }


    /*@RequestMapping("person/${id}/addContract")
    public ModelAndView addContract(@PathVariable("id") Integer id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("person", this.personService.getPersonById(id));
            modelAndView.addObject("tariffSet",this.tariffService.getOptionSet(id));
            modelAndView.setViewName("person/personEdit");
            return modelAndView;
        } catch (DataExistenceException e) {
            return null;
        }
    }*/

}
