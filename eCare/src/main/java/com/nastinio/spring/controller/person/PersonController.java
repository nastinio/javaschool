package com.nastinio.spring.controller.person;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.OptionCellular;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.*;
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

        OptionCellular option = this.optionCellularService.getById(idOption);
        modelAndView.addObject("listAllJointlyOptions",this.optionCellularService.getAllJointlyOptions(option));
        modelAndView.addObject("listAllExcludeOptions",this.optionCellularService.getAllExcludeOptions(option));

        return modelAndView;
    }



    //УДАЛЕНИЕ ДОПОЛНИТЕЛЬНОЙ ОПЦИИ ИЗ ТАРИФА
    /*@RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-{idContract}/extraoption-{idOption}-disable", method = RequestMethod.GET)
    public String clientContractDisableOption(@PathVariable Integer idPerson, @PathVariable Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.removeExtraOptionFromContract(idContract,idOption);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"-more";
    }*/



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


    //ПОДКЛЮЧЕНИЕ И ОТКЛЮЧЕНИЕ ДОПОЛНИТЕЛЬНЫХ ОПЦИЙ
    //ecare/person-${person.id}/contract-${contract.id}-exrtaoptions-add


    //СМЕНА ТАРИФА ЧЕРЕЗ КОРЗИНУ
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/tariff-{idTariff}/basket-add", method = RequestMethod.GET)
    public String changeTariff(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        Contract contract= this.contractService.getById(idContract);
        contract.setTariffInContractForChange(this.tariffService.getById(idTariff));
        this.contractService.update(contract);

        //this.contractService.changeTariff(idContract,idTariff);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket", method = RequestMethod.GET)
    public ModelAndView showBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/basket");
        modelAndView.addObject("person",this.personService.getById(idPerson));

        Contract contract = this.contractService.getById(idContract);
        modelAndView.addObject("contract",contract);

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/update",method = RequestMethod.GET)
    public String updateContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.updateTariffByPerson(idContract);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"-more";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket-reset",method = RequestMethod.GET)
    public String resetBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.resetTariffChange(idContract);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"/basket";
    }

    //ДОБАВЛЕНИЕ ОПЦИЙ ЧЕРЕЗ КОРЗИНУ
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/option-{idOption}/basket-add", method = RequestMethod.GET)
    public String addOptionToBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idTariff") Integer idTariff) throws DataExistenceException {


        //this.contractService.changeTariff(idContract,idTariff);

        return "redirect:/ecare/person-"+idPerson+"/contract-"+idContract+"/basket";
    }
}

