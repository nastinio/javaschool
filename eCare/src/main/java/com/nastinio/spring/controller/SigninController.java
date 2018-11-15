/*
package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
@ComponentScan("com.nastinio")
public class SigninController {

    @Autowired
    SigninService signinService;

    */
/*First method on start application*//*

    */
/*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) *//*

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("person", new Person());
        modelAndView.setViewName("signin/signinPerson");
        return modelAndView;
    }

    @RequestMapping(value = "/signinAsManager", method = RequestMethod.GET)
    public ModelAndView signinAsManager() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("manager", new Manager());
        modelAndView.setViewName("signin/signinManager");
        return modelAndView;
    }

    */
/*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-person">,
    то попадем вот сюда
     *//*

    @RequestMapping(value = "/check-person", method = RequestMethod.POST)
    public ModelAndView checkPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            // TODO: уведомить пользователя о том, что данные невалидны
            modelAndView.setViewName("signin/signinPerson");
            //modelAndView.addObject("errorMessage","Некорректные данные для входа");
            return modelAndView;
        }
        if (signinService.isRegisteredPerson(person)) {
            signinService.doOnline(person.getId());
            modelAndView.setViewName("redirect:/ecare/"+person.getId()+"/info");
            return modelAndView;
            */
/*try {
                modelAndView.addObject("person", signinService.getPersonById(person.getId()));
                modelAndView.setViewName("redirect:/ecare");
                person.setOnline(true);
                return modelAndView;
            } catch (DataExistenceException e) {
                modelAndView.setViewName("signin/signinPerson");
                //modelAndView.addObject("errorMessage","Некорректные данные для входа");
                return modelAndView;
            }*//*


        }
        modelAndView.setViewName("signin/signinPerson");
        return modelAndView;

    }

    @RequestMapping(value = "/check-manager")
    public ModelAndView checkManager(@ModelAttribute("manager") @Valid Manager manager, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            // TODO: уведомить пользователя о том, что данные невалидны
            modelAndView.setViewName("signin/signinManager");
            //modelAndView.addObject("errorMessage","Некорректные данные для входа");
            return modelAndView;
        }
        if (signinService.isRegisteredManager(manager)) {
            modelAndView.setViewName("redirect:/ecareManager");
            manager.setSignin(true);
            modelAndView.addObject("manager", manager);
            return modelAndView;
        }
        modelAndView.setViewName("signin/signinManager");
        //modelAndView.addObject("errorMessage","Некорректные данные для входа");
        return modelAndView;

    }

    @RequestMapping(value = "/ecare/{id}/logout")
    public String logout(@PathVariable("id") Integer id) throws DataExistenceException {
        signinService.doOffline(id);
        return "redirect:/signin";

    }


}
*/
