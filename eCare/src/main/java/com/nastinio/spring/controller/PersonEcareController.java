package com.nastinio.spring.controller;

import com.nastinio.spring.dao.ContractDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.ContractService;
import com.nastinio.spring.service.EcareService;
import com.nastinio.spring.service.PersonService;
import com.nastinio.spring.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "/ecare/{id}", method = RequestMethod.GET)
    public ModelAndView showEcare(@PathVariable("id") Integer id) throws DataExistenceException {
        Person person = personService.getPersonById(id);

        ModelAndView modelAndView = new ModelAndView();

        //А ля security
        /*if (!person.isOnline()) {
            modelAndView.setViewName("redirect:/signin");
            return modelAndView;
        }*/

        modelAndView.addObject("person", person);
        //Берем все контракты для данного пользователя и каждому контракту задаем тариф
        modelAndView.addObject("contractSet", ecareService.getContractWithTariffList(id));


        modelAndView.setViewName("ecarePerson");

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editEcare(@ModelAttribute("person") Person person) throws DataExistenceException {
        System.out.println("Получили для обновления: " + person.toString());
        personService.updatePerson(person);

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("person", person);
        modelAndView.setViewName("redirect:/ecare/" + person.getId());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/{idPerson}/block/{idContract}", method = RequestMethod.GET)
    public String blockContract(@PathVariable("idPerson") Integer idPerson,@PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.blockContractByPerson(idContract);
        return "redirect:/ecare/"+idPerson;
    }

    @RequestMapping(value = "/ecare/{idPerson}/unlock/{idContract}", method = RequestMethod.GET)
    public String unlockContract(@PathVariable("idPerson") Integer idPerson,@PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.unlockContractByPerson(idContract);
        return "redirect:/ecare/"+idPerson;
    }


}
