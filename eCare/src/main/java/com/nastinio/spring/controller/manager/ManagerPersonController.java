package com.nastinio.spring.controller.manager;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan("com.nastinio")
public class ManagerPersonController {
    @Autowired
    PersonService personService;

    /*
     * Управление пользователями
     * */
    @RequestMapping(value = "/ecare/manager/all-persons", method = RequestMethod.GET)
    public ModelAndView managerAllPersons() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/persons");
        modelAndView.addObject("personsList",this.personService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}-more", method = RequestMethod.GET)
    public ModelAndView managerPersonMore(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/personMore");
        modelAndView.addObject("person",this.personService.getById(idPerson));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}-remove", method = RequestMethod.GET)
    public String managerPersonRemove(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        this.personService.remove(idPerson);
        return "redirect:/ecare/manager/all-persons";
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}-edit", method = RequestMethod.GET)
    public ModelAndView managerPersonEdit(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/personEditAdd");
        modelAndView.addObject("person",this.personService.getById(idPerson));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/person-update", method = RequestMethod.POST)
    public String managerPersonUpdate(@ModelAttribute("person") Person person){
        System.out.println("Получили для обновления: " + person.toString());
        // TODO NPE!
        if (person.getId() == null) {
            this.personService.add(person);
            return "redirect:/ecare/manager/all-persons";
        } else {
            this.personService.update(person);
        }
        this.personService.update(person);

        return "redirect:/ecare/manager/person-"+person.getId()+"-more";
    }

    @RequestMapping(value = "/ecare/manager/person-add", method = RequestMethod.GET)
    public ModelAndView managerPersonAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/personEditAdd");
        modelAndView.addObject("person",new Person());

        return modelAndView;
    }
}
