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
    public String home() {
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

        //Нужно передать список опций в контракте с проставленным полем о возможности удаления
        modelAndView.addObject("optionsOnContract",this.contractService.getOptionsOnContractWithDisableStatus(idContract));

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
        modelAndView.addObject("optionsList", this.optionCellularService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "ecare/person-{idPerson}/contract-{idContract}/option-{idOption}-more", method = RequestMethod.GET)
    public ModelAndView managerOptionMore(@PathVariable Integer idPerson, @PathVariable Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/clientOptionMore");

        OptionCellular option = this.optionCellularService.getById(idOption);
        Contract contract = this.contractService.getById(idContract);

        option.setCanBeConnectedByPerson(this.optionCellularService.checkCanBeAddInBasketForAdd(option, contract));
        option.setStatus(this.optionCellularService.checkStatus(option, contract));

        modelAndView.addObject("person", this.personService.getById(idPerson));
        modelAndView.addObject("contract", contract);
        modelAndView.addObject("option", option);

        //modelAndView.addObject("listAllJointlyOptions",this.optionCellularService.getAllJointlyOptions(option));
        modelAndView.addObject("listAllJointlyOptions", this.optionCellularService.getJointlyOptionsWithStatus(option, contract));
        modelAndView.addObject("listAllExcludeOptions", this.optionCellularService.getExcludeOptionsWithStatus(option, contract));

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
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "-more";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}-unlock", method = RequestMethod.GET)
    public String unlockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.unlockContractByPerson(idContract);
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "-more";
    }


    //СМЕНА ТАРИФА ЧЕРЕЗ КОРЗИНУ
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/tariff-{idTariff}/basket-add", method = RequestMethod.GET)
    public String changeTariff(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idTariff") Integer idTariff) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        contract.setTariffInContractForChange(this.tariffService.getById(idTariff));
        this.contractService.update(contract);

        //this.contractService.changeTariff(idContract,idTariff);

        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket", method = RequestMethod.GET)
    public ModelAndView showBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/basket");
        modelAndView.addObject("person", this.personService.getById(idPerson));

        Contract contract = this.contractService.getById(idContract);
        //Проверить все опции в корзине: нет ли конфликтов и можно ли их добавлять/удалять
        contract.setCanApplyChangesFromBasket(this.contractService.checkValidityChangesFromBasket(contract));

        modelAndView.addObject("contract", contract);

        modelAndView.addObject("optionsForAdd", contract.getOptionsForAdd());
        modelAndView.addObject("optionsForRemove", contract.getOptionsForRemove());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/update", method = RequestMethod.GET)
    public String updateContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.updateTariffByPerson(idContract);

        Contract contract = this.contractService.getById(idContract);
        this.contractService.moveOptionsFromBasketToContract(contract);

        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "-more";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Сброс корзины
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket-reset-tariff", method = RequestMethod.GET)
    public String resetBasketTariff(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.resetTariffChange(idContract);
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket-reset-option-add-{idOption}", method = RequestMethod.GET)
    public String resetBasketOptionAdd(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.resetOptionForAdd(idContract, idOption);
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket-reset-option-remove-{idOption}", method = RequestMethod.GET)
    public String resetBasketOptionRemove(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.resetOptionForRemove(idContract, idOption);
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/basket-reset", method = RequestMethod.GET)
    public String resetBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.resetBasketAll(idContract);
        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ДОБАВЛЕНИЕ ОПЦИЙ ЧЕРЕЗ КОРЗИНУ
    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/option-{idOption}/basket-add", method = RequestMethod.GET)
    public String addOptionToBasket(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        this.contractService.addOptionInBasket(contract, option);

        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }

    @RequestMapping(value = "/ecare/person-{idPerson}/contract-{idContract}/option-{idOption}/basket-disable", method = RequestMethod.GET)
    public String addOptionToBasketForRemove(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        OptionCellular option = this.optionCellularService.getById(idOption);

        this.contractService.addOptionInBasketForRemove(contract, option);

        return "redirect:/ecare/person-" + idPerson + "/contract-" + idContract + "/basket";
    }
}

