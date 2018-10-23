package com.nastinio.spring.controller;

import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.PersonService;
import com.nastinio.spring.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SetupController {
    private SetupService setupService;

    @Autowired(required=true)
    @Qualifier(value="setupService")
    public void setSetupService(SetupService ps){
        this.setupService = ps;
    }



    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */
    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("person", new Person());
        modelAndView.setViewName("setup");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-person">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-person")
    public ModelAndView checkPerson(@ModelAttribute("person") Person person) {
        System.out.println("checkPerson: получили на вход "+ person.toString());
        ModelAndView modelAndView = new ModelAndView();
        if(setupService.isRegisteredPerson(person)){

            //имя представления, куда нужно будет перейти
            modelAndView.setViewName("ecarePerson");
            //записываем в атрибут userJSP (используется на странице *.jsp объект person
            modelAndView.addObject("person", person);
            return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
        }else{
            modelAndView.setViewName("setup");
            return modelAndView;
        }
    }

    /*@RequestMapping(value = "/ecarePerson", method = RequestMethod.GET)
    public ModelAndView startEcare(ModelAndView modelAndView) {
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("person", new Person());
        modelAndView.setViewName("ecarePerson");
        return modelAndView;
    }*/


}
