package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class SigninController {
    private SigninService signinService;

    @Autowired(required = true)
    @Qualifier(value = "signinService")
    public void setSigninService(SigninService signinService) {
        this.signinService = signinService;
    }


    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */
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

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-person">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-person", method = RequestMethod.POST)
    public ModelAndView checkPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            // TODO: уведомить пользователя о том, что данные невалидны
            modelAndView.setViewName("signin/signinPerson");
            //modelAndView.addObject("errorMessage","Некорректные данные для входа");
            return modelAndView;
        }
        if (signinService.isRegisteredPerson(person)) {
            try {
                modelAndView.setViewName("person/ecarePerson");
                modelAndView.addObject("person", signinService.getPersonById(person.getId()));
                return modelAndView;
            } catch (DataExistenceException e) {
                modelAndView.setViewName("signin/signinPerson");
                //modelAndView.addObject("errorMessage","Некорректные данные для входа");
                return modelAndView;
            }

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
            modelAndView.addObject("manager", manager);
            return modelAndView;
        }
        modelAndView.setViewName("signin/signinManager");
        //modelAndView.addObject("errorMessage","Некорректные данные для входа");
        return modelAndView;

    }


}
