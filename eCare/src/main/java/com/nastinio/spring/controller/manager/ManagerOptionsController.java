package com.nastinio.spring.controller.manager;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.OptionCellular;
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
public class ManagerOptionsController {
    @Autowired
    OptionCellularService optionCellularService;

    /*
     * Управление опциями
     * */
    @RequestMapping(value = "/ecare/manager/all-options", method = RequestMethod.GET)
    public ModelAndView managerAllOptions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/options");
        modelAndView.addObject("optionsList",optionCellularService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/option-{idOption}-more", method = RequestMethod.GET)
    public ModelAndView managerOptionMore(@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/optionMore");
        modelAndView.addObject("option",optionCellularService.getById(idOption));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/option-{idOption}-remove", method = RequestMethod.GET)
    public String managerOptionRemove(@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        optionCellularService.remove(idOption);
        return "redirect:/ecare/manager/all-options";
    }

    @RequestMapping(value = "/ecare/manager/option-{idOption}-edit", method = RequestMethod.GET)
    public ModelAndView managerOptionEdit(@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/optionEditAdd");
        modelAndView.addObject("option",optionCellularService.getById(idOption));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/option-update", method = RequestMethod.POST)
    public String managerOptionUpdate(@ModelAttribute("option") OptionCellular option){
        System.out.println("Получили для обновления: " + option.toString());
        // TODO NPE!
        if (option.getId() == null) {
            this.optionCellularService.add(option);
            return "redirect:/ecare/manager/all-options";
        } else {
            this.optionCellularService.update(option);
        }
        this.optionCellularService.update(option);

        return "redirect:/ecare/manager/option-"+option.getId()+"-more";
    }

    @RequestMapping(value = "/ecare/manager/option-add", method = RequestMethod.GET)
    public ModelAndView managerOptionAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/optionEditAdd");
        modelAndView.addObject("option",new OptionCellular());

        return modelAndView;
    }
}
