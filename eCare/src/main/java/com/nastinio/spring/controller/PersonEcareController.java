package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@ComponentScan("com.nastinio")
public class PersonEcareController {

    @Autowired
    PersonService personService;

    @Autowired
    ContractService contractService;

    @Autowired
    TariffService tariffService;

    @Autowired
    EcareService ecareService;

    @Autowired
    OptionService optionService;


    /*@RequestMapping(value = "/ecare/{id}", method = RequestMethod.GET)
    public ModelAndView showEcare(@PathVariable("id") Integer id) throws DataExistenceException {
        Person person = personService.getPersonById(id);
        Person cutPerson = new Person(person.getId());
        cutPerson.setFirstname(person.getFirstname());
        cutPerson.setLastname(person.getLastname());

        ModelAndView modelAndView = new ModelAndView();

        //А ля security
        *//*if (!person.isOnline()) {
            modelAndView.setViewName("redirect:/signin");
            return modelAndView;
        }*//*

        modelAndView.addObject("person", cutPerson);
        //Берем все контракты для данного пользователя и каждому контракту задаем тариф
        modelAndView.addObject("contractSet", ecareService.getContractWithTariffList(id));

        modelAndView.addObject("optionSet", tariffService.getOptionsSet(1));


        modelAndView.setViewName("ecarePerson");

        return modelAndView;
    }*/


    @RequestMapping(value = "/ecare/{id}/info", method = RequestMethod.GET)
    public ModelAndView showEcareInfo(@PathVariable("id") Integer id) throws DataExistenceException {
        Person person = personService.getPersonById(id);

        ModelAndView modelAndView = new ModelAndView();

        //А ля security
        /*if (!person.isOnline()) {
            modelAndView.setViewName("redirect:/signin");
            return modelAndView;
        }*/

        modelAndView.addObject("person", person);


        modelAndView.setViewName("ecarePerson");

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/{id}/contracts", method = RequestMethod.GET)
    public ModelAndView showEcareContracts(@PathVariable("id") Integer id) throws DataExistenceException {
        Person person = personService.getPersonById(id);
        Person cutPerson = new Person(person.getId());
        cutPerson.setFirstname(person.getFirstname());
        cutPerson.setLastname(person.getLastname());

        ModelAndView modelAndView = new ModelAndView();

        //А ля security
        /*if (!person.isOnline()) {
            modelAndView.setViewName("redirect:/signin");
            return modelAndView;
        }*/

        modelAndView.addObject("person", cutPerson);
        //Берем все контракты для данного пользователя и каждому контракту задаем тариф
        modelAndView.addObject("contractSet", ecareService.getContractWithTariffList(id));

        //modelAndView.addObject("optionSet", tariffService.getOptionsSet(1));


        modelAndView.setViewName("ecarePerson");

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editEcare(@ModelAttribute("person") Person person) throws DataExistenceException {
        System.out.println("Получили для обновления: " + person.toString());
        personService.updatePerson(person);

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("person", person);
        modelAndView.setViewName("redirect:/ecare/" + person.getId() + "/info");

        return modelAndView;
    }


    @RequestMapping(value = "/ecare/{idPerson}/block/{idContract}", method = RequestMethod.GET)
    public String blockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.blockContractByPerson(idContract);
        return "redirect:/ecare/" + idPerson + "/contracts";
    }

    @RequestMapping(value = "/ecare/{idPerson}/unlock/{idContract}", method = RequestMethod.GET)
    public String unlockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.unlockContractByPerson(idContract);
        return "redirect:/ecare/" + idPerson + "/contracts";
    }


    @RequestMapping(value = "/ecare/{idPerson}/contract/{idContract}/more", method = RequestMethod.GET)
    public ModelAndView contractDetails(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        Person person = personService.getPersonById(idPerson);

        ModelAndView modelAndView = new ModelAndView();

        //А ля security
        /*if (!person.isOnline()) {
            modelAndView.setViewName("redirect:/signin");
            return modelAndView;
        }*/

        modelAndView.addObject("person", person);
        //Передадим контракт с подцепленным тарифом
        modelAndView.addObject("contract", this.ecareService.getTariffForContractByIdContract(idContract));
        modelAndView.setViewName("ecareContractDetails");

        return modelAndView;
    }


    /*Отключение дополнительной опции в контракте*/
    @RequestMapping(value = "/ecare/{idPerson}/contract/{idContract}/disableExtraOption/{idOption}")
    public String disableExtraOption(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract,
                                     @PathVariable("idOption") Integer idOption) {
        //TODO:Удалим строку из таблицы `option_contract`
        //this.optionContractDAO.deleteExtraOptionByIdContract(idContract,idOption);


        return "redirect:/ecare/" + idPerson + "/contract/" + idContract + "/more";
    }

}
