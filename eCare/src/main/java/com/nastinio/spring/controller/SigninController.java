package com.nastinio.spring.controller;

import com.nastinio.spring.model.Manager;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SigninController {
    private SigninService signinService;

    @Autowired(required=true)
    @Qualifier(value="signinService")
    public void setSigninService(SigninService signinService){
        this.signinService = signinService;
    }


    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннотации и настройки пути после деплоя) */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("person", new Person());
        modelAndView.setViewName("signinBootstrap");
        return modelAndView;
    }
    @RequestMapping(value = "/signinAsManager", method = RequestMethod.GET)
    public ModelAndView signinAsManager() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("manager", new Manager());
        modelAndView.setViewName("signinAsManager");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-person">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-person")
    public ModelAndView checkPerson(@ModelAttribute("person") Person person) {
        System.out.println("checkPerson: получили " + person.toString());
        ModelAndView modelAndView = new ModelAndView();
        if(signinService.isRegisteredPerson(person)){
            //имя представления, куда нужно будет перейти
            modelAndView.setViewName("ecarePerson");
            //записываем в атрибут userJSP (используется на странице *.jsp объект person
            modelAndView.addObject("person", person);
            return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
        }else{
            modelAndView.setViewName("signinBootstrap");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/check-manager")
    public ModelAndView checkManager(@ModelAttribute("manager") Manager manager) {
        System.out.println("checkManager: получили " + manager.toString());
        ModelAndView modelAndView = new ModelAndView();
        if(signinService.isRegisteredManager(manager)){
            //имя представления, куда нужно будет перейти
            modelAndView.setViewName("ecarePerson");
            //записываем в атрибут userJSP (используется на странице *.jsp объект manager
            modelAndView.addObject("manager", manager);
            return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
        }else{
            modelAndView.setViewName("signinBootstrap");
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
