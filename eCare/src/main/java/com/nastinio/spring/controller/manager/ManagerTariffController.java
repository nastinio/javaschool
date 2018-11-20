package com.nastinio.spring.controller.manager;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Tariff;
import com.nastinio.spring.service.OptionCellularService;
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
public class ManagerTariffController {
    @Autowired
    TariffService tariffService;

    @Autowired
    OptionCellularService optionCellularService;

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


        modelAndView.addObject("listOptions",this.tariffService.getListAvailableOptionsForAdd(idTariff));
        //modelAndView.addObject("listOptions",this.optionCellularService.getList());

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
    public String managerTariffUpdate(@ModelAttribute("tariff") Tariff tariff) throws DataExistenceException {
        System.out.println("Получили для обновления: " + tariff.toString());
        // TODO NPE!
        if (tariff.getId() == null) {
            this.tariffService.add(tariff);
            return "redirect:/ecare/manager/all-tariffs";
        } else {
            Tariff temp = this.tariffService.getById(tariff.getId());
            tariff.setOptionsOnTariff(temp.getOptionsOnTariff());
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

    @RequestMapping(value = "/ecare/manager/tariff-{idTariff}/option-{idOption}-activate", method = RequestMethod.GET)
    public String managerTariffActivateOption(@PathVariable("idTariff") Integer idTariff,@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.tariffService.includeOptionInTariff(idTariff,idOption);

        return "redirect:/ecare/manager/tariff-"+idTariff+"-more";
    }
}
