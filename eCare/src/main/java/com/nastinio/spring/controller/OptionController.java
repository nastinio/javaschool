package com.nastinio.spring.controller;


import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Option;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OptionController {

    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/options", method = RequestMethod.GET)
    public String listOptions(Model model) {
        model.addAttribute("listOptions", this.optionService.list());
        return "options/optionAll";
    }

    @RequestMapping("/option/{id}")
    public ModelAndView editPerson(@PathVariable("id") Integer id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("option", this.optionService.getById(id));

            modelAndView.addObject("listJointlyOptions", this.optionService.getJointlyOptionsSet(id));
            modelAndView.addObject("listExcludeOptions", this.optionService.getExcludeOptionsSet(id));

            modelAndView.setViewName("options/option");
            return modelAndView;

        } catch (DataExistenceException e) {
            return null;
        }

    }

    @RequestMapping("/option/edit/{id}")
    public ModelAndView editOption(@PathVariable("id") Integer id) {
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("option", this.optionService.getById(id));
            modelAndView.addObject("listOptions", this.optionService.list());


            modelAndView.addObject("listJointlyOptions", this.optionService.getJointlyOptionsSet(id));
            modelAndView.addObject("listExcludeOptions", this.optionService.getExcludeOptionsSet(id));

            modelAndView.addObject("listOptionsWithoutRules", this.optionService.getOptionsWithoutRulesSet(id));

            modelAndView.setViewName("options/optionEdit");
            return modelAndView;
        } catch (DataExistenceException e) {
            //По идее не случится, т.к. берем по точно существующему id
            return null;
        }

    }

    @RequestMapping("/option/add")
    public ModelAndView addOption() throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("listOptions", this.optionService.list());
        modelAndView.addObject("option", new Option());
        modelAndView.setViewName("options/optionEdit");
        return modelAndView;
        //return "options/optionEdit";
    }

    @RequestMapping(value = "/option/create", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("option") Option option) {

        if (option.getId() == null) {
            //new person, add it
            this.optionService.add(option);
        } else {
            //existing person, call update
            this.optionService.update(option);
        }

        return "redirect:/options";
        /*Integer id = option.getId();
        return "redirect:/options/option/edit{"+id+"}";*/

    }

    /*@RequestMapping("/option/remove/{id}")
    public String removeOption(@PathVariable("id") Integer id) {

        this.optionService.remove(id);
        return "redirect:/options";
    }*/


}
