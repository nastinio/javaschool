package com.nastinio.spring.controller;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Tariff;
import com.nastinio.spring.service.TariffService;
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
public class TempController {
    @Autowired
    TariffService tariffService;

    /*
    * Управление тарифами
    * */
    @RequestMapping(value = "/ecare/manager/all-tariffs", method = RequestMethod.GET)
    public ModelAndView managerAllTariffs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/tariffs");
        modelAndView.addObject("tariffsList",this.tariffService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/tariff-{idTariff}-more", method = RequestMethod.GET)
    public ModelAndView managerTariffMore(@PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/tariffMore");
        modelAndView.addObject("tariff",this.tariffService.getById(idTariff));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/tariff-{idTariff}-remove", method = RequestMethod.GET)
    public String managerTariffRemove(@PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        this.tariffService.remove(idTariff);
        return "redirect:/ecare/manager/all-tariffs";
    }

    @RequestMapping(value = "/ecare/manager/tariff-{idTariff}-edit", method = RequestMethod.GET)
    public ModelAndView managerTariffEdit(@PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/tariffEditAdd");
        modelAndView.addObject("tariff",this.tariffService.getById(idTariff));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/tariff-update", method = RequestMethod.POST)
    public String managerTariffUpdate(@ModelAttribute("tariff") Tariff tariff){
        System.out.println("Получили для обновления: " + tariff.toString());
        // TODO NPE!
        if (tariff.getId() == null) {
            this.tariffService.add(tariff);
            return "redirect:/ecare/manager/all-tariffs";
        } else {
            this.tariffService.update(tariff);
        }
        this.tariffService.update(tariff);

        return "redirect:/ecare/manager/tariff-"+tariff.getId()+"-more";
    }

    @RequestMapping(value = "/ecare/manager/tariff-add", method = RequestMethod.GET)
    public ModelAndView managerTariffAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/tariffEditAdd");
        modelAndView.addObject("tariff",new Tariff());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/tariff-{idTariff}/option-{idOption}-disable", method = RequestMethod.GET)
    public String managerTariffDisableOption(@PathVariable("idTariff") Integer idTariff,@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.tariffService.removeOptionFromTariff(idTariff,idOption);

        return "redirect:/ecare/manager/tariff-"+idTariff+"-more";
    }






    /*
     * Управление пользователями
     * */
    @RequestMapping(value = "/ecare/manager/all-persons", method = RequestMethod.GET)
    public void managerAllPersons() {

    }




}
