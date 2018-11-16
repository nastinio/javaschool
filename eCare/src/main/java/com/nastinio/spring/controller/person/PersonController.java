package com.nastinio.spring.controller.person;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.ContractService;
import com.nastinio.spring.service.OptionCellularService;
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

@Controller
@ComponentScan("com.nastinio")
public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    ContractService contractService;

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionCellularService optionCellularService;

    @RequestMapping("/")
    public String home(){
        return "hello";
    }

    /*
     *
     * /ecare/person-{idPerson}/contract-all                                                    done
     * /ecare/person-{idPerson}/contract-{idContract}-more                                      done
     * /ecare/person-{idPerson}/info-edit                                                       done
     * /ecare/person-{idPerson}/contract-{idContract}-block
     * /ecare/person-{idPerson}/contract-{idContract}-unlock                                    done
     *
     * /ecare/person-{idPerson}/contract-{idContract}/tariff-change
     * /ecare/person-{idPerson}/contract-{idContract}/tariff-{idTariff}-more                    done
     * /ecare/person-{idPerson}/contract-{idContract}/option-{idOption}-more                    done
     * /ecare/manager/person-{idPerson}/contract-{idContract}/extraoption-{idOption}-disable    done
     *
     * /ecare/person-{idPerson}/contract-{idContract}/option-all                                 done
     * /ecare/person-{idPerson}/contract-{idContract}/tariff-all                                 done
     *
     * */
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-all", method = RequestMethod.GET)
    public ModelAndView personContractAll(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientMore");
        modelAndView.addObject("person", this.personService.getById(idPerson));

        return modelAndView;
    }

    //ecare/person-{idPerson}/info-edit
    @RequestMapping(value = "/ecare/person-{idPerson}/info-edit", method = RequestMethod.GET)
    public ModelAndView clientPersonEdit(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientEdit");
        modelAndView.addObject("person", this.personService.getById(idPerson));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/info-update", method = RequestMethod.POST)
    public String clientPersonUpdate(@ModelAttribute("person") Person person) {
        System.out.println("Получили для обновления: " + person.toString());
        // TODO NPE!
        this.personService.update(person);

        return "redirect:/ecare/person-" + person.getId() + "/contract-all";
    }

    //ecare/person-{idPerson}/contract-{idContract}-more
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}-more", method = RequestMethod.GET)
    public ModelAndView clientContractMore(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientContractMore");
        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", this.contractService.getById(idContract));

        return modelAndView;
    }


    //УПРАВЛЕНИЕ ОБЩЕЙ ИНФОРМАЦИЕЙ ОБ ОПЦИЯХ И ТАРИФАХ
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/tariff-all", method = RequestMethod.GET)
    public ModelAndView clientAllTariffs(@PathVariable Integer idPerson, @PathVariable Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientTariffs");

        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", this.contractService.getById(idContract));
        modelAndView.addObject("tariffsList", this.tariffService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "ecare/person-{idPerson}/contract-{idContract}/tariff-{idTariff}-more", method = RequestMethod.GET)
    public ModelAndView clientTariffMore(@PathVariable Integer idPerson, @PathVariable Integer idContract, @PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientTariffMore");

        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", this.contractService.getById(idContract));
        modelAndView.addObject("tariff", this.tariffService.getById(idTariff));

        return modelAndView;
    }

    @RequestMapping(value = "ecare/person-{idPerson}/contract-{idContract}/option-all", method = RequestMethod.GET)
    public ModelAndView clientAllOptions(@PathVariable Integer idPerson, @PathVariable Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientOptions");

        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", this.contractService.getById(idContract));
        modelAndView.addObject("optionsList",this.optionCellularService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "ecare/person-{idPerson}/contract-{idContract}/option-{idOption}-more", method = RequestMethod.GET)
    public ModelAndView managerOptionMore(@PathVariable Integer idPerson, @PathVariable Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientOptionMore");

        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", this.contractService.getById(idContract));
        modelAndView.addObject("option",this.optionCellularService.getById(idOption));

        return modelAndView;
    }



    //УДАЛЕНИЕ ДОПОЛНИТЕЛЬНОЙ ОПЦИИ ИЗ ТАРИФА
    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-{idContract}/extraoption-{idOption}-disable", method = RequestMethod.GET)
    public String clientContractDisableOption(@PathVariable Integer idPerson, @PathVariable Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.removeExtraOptionFromContract(idContract,idOption);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"-more";
    }



    //БЛОКИРОВКА
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}-block", method = RequestMethod.GET)
    public String blockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.blockContractByPerson(idContract);
        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"-more";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}-unlock", method = RequestMethod.GET)
    public String unlockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.unlockContractByPerson(idContract);
        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"-more";
    }


}

