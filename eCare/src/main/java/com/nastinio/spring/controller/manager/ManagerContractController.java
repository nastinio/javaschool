package com.nastinio.spring.controller.manager;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.OptionCellular;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.model.Tariff;
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

import java.util.Set;

@Controller
@ComponentScan("com.nastinio")
public class ManagerContractController {
    @Autowired
    ContractService contractService;

    @Autowired
    PersonService personService;

    @Autowired
    TariffService tariffService;

    @Autowired
    OptionCellularService optionCellularService;

    /*
     * Управление контрактами
     * */
    @RequestMapping(value = "/ecare/manager/all-contracts", method = RequestMethod.GET)
    public ModelAndView managerAllContracts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contracts");
        modelAndView.addObject("contractsList", this.contractService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/contract-{idContract}-more", method = RequestMethod.GET)
    public ModelAndView managerContractMore(@PathVariable("idContract") Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contractMore");
        modelAndView.addObject("contract", this.contractService.getById(idContract));

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/contract-{idContract}-remove", method = RequestMethod.GET)
    public String managerContractRemove(@PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.remove(idContract);
        return "redirect:/ecare/manager/all-contracts";
    }

    @RequestMapping(value = "/ecare/manager/contract-{idContract}-edit", method = RequestMethod.GET)
    public ModelAndView managerPContractEdit(@PathVariable("idContract") Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contractEditAdd");
        Contract contract = this.contractService.getById(idContract);
        modelAndView.addObject("contract", contract);
        modelAndView.addObject("idPerson", contract.getPersonInContract().getId());
        modelAndView.addObject("tariffs", this.tariffService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-update", method = RequestMethod.POST)
    public String managerContractUpdate(@ModelAttribute("contract") Contract contract, @PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        // TODO NPE!
        System.out.println("Получили контракт: " + contract.getId() + " " + contract.getNumber() + " " + contract.getIdTariff());

        if (contract.getId() == null) {
            this.contractService.addForPerson(contract, idPerson);
            return "redirect:/ecare/manager/person-" + idPerson + "-more";
        } else {
            Person person = this.personService.getById(idPerson);
            Tariff tariff = this.tariffService.getById(contract.getIdTariff());
            contract.setPersonInContract(person);
            contract.setTariffInContract(tariff);
            this.contractService.update(contract);
            //this.contractService.updateWithTariff(contract, idPerson);
            return "redirect:/ecare/manager/contract-" + contract.getId() + "-more";
        }
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-add", method = RequestMethod.GET)
    public ModelAndView managerContractAdd(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contractEditAdd");
        modelAndView.addObject("idPerson", idPerson);
        modelAndView.addObject("contract", new Contract());
        modelAndView.addObject("tariffs", this.tariffService.getList());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/contract-{idContract}/extraoption-{idOption}-disable", method = RequestMethod.GET)
    public String managerTariffDisableOption(@PathVariable("idContract") Integer idContract, @PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.removeExtraOptionFromContract(idContract, idOption);

        return "redirect:/ecare/manager/contract-" + idContract + "-more";
    }

    //ecare/manager/person-${contract.personInContract.id}/contract-${contract.id}-block
    //БЛОКИРОВКА
    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-{idContract}-block", method = RequestMethod.GET)
    public String blockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.blockContractByManager(idContract);
        return "redirect:/ecare/manager/contract-" + idContract + "-more";
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-{idContract}-unlock", method = RequestMethod.GET)
    public String unlockContract(@PathVariable("idPerson") Integer idPerson, @PathVariable("idContract") Integer idContract) throws DataExistenceException {
        this.contractService.unlockContractByManager(idContract);
        return "redirect:/ecare/manager/contract-" + idContract + "-more";
    }

    @RequestMapping(value = "/ecare/manager/contract-search", method = RequestMethod.GET)
    public ModelAndView searchContract(@ModelAttribute("number") String number) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/searchResults");
        modelAndView.addObject("listContracts", this.contractService.getSearchList(number));
        return modelAndView;
    }

    @RequestMapping(value = "ecare/manager/contract-{idContract}-exrtaoptions-add", method = RequestMethod.GET)
    public ModelAndView addExtraOptionsContract(@PathVariable Integer idContract) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contractAddExtraOptions");

        //Проставить зависимости в дополнительных опциях контракта
        Contract contract = this.contractService.getContractWithDependenciesOnOptions(idContract);
        modelAndView.addObject("contract", this.contractService.getById(idContract));

        //Список всех подключенных доп. опций с зависимостями и информацией, можно ли ее отключить
        modelAndView.addObject("optionsOnContract",this.optionCellularService.getExtraOptionsListForDisabled(idContract));
        //Список всех опций невключенных в тариф или неподключенных как дополнительные с проставленными зависимостями
        modelAndView.addObject("extraOptionsList",this.optionCellularService.getExtraOptionsListForAdded(idContract));


        return modelAndView;
    }


    @RequestMapping(value = "ecare/manager/contract-{idContract}-exrtaoptions-add/option-{idOption}", method = RequestMethod.GET)
    public String addExtraOptionContract(@PathVariable Integer idContract,@PathVariable Integer idOption) throws DataExistenceException {
        this.contractService.addExtraOptionToContract(idContract,idOption);
        return "redirect:/ecare/manager/contract-"+idContract+"-exrtaoptions-add";

    }

    @RequestMapping(value = "ecare/manager/contract-{idContract}-exrtaoptions-delete/option-{idOption}", method = RequestMethod.GET)
    public String deleteExtraOptionContract(@PathVariable Integer idContract,@PathVariable Integer idOption) throws DataExistenceException {
        System.out.println("Отключить опцию "+idOption+" от контракта "+idContract);
        this.contractService.disableExtraOptionToContract(idContract,idOption);
        return "redirect:/ecare/manager/contract-"+idContract+"-exrtaoptions-add";
    }
}
