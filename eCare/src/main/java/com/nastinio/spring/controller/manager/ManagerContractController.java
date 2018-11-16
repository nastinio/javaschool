package com.nastinio.spring.controller.manager;

import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Person;
import com.nastinio.spring.service.ContractService;
import com.nastinio.spring.service.PersonService;
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
public class ManagerContractController {
    @Autowired
    ContractService contractService;

    @Autowired
    PersonService personService;

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
        modelAndView.addObject("idPerson",contract.getId());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-update", method = RequestMethod.POST)
    public String managerContractUpdate(@ModelAttribute("contract") Contract contract,@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        // TODO NPE!
        if (contract.getId() == null) {
            this.contractService.addForPerson(contract,idPerson);
            return "redirect:/ecare/manager/person-"+idPerson+"-more";
        } else {
            this.contractService.update(contract);
            return "redirect:/ecare/manager/contract-" + contract.getId() + "-more";
        }
    }

    @RequestMapping(value = "/ecare/manager/person-{idPerson}/contract-add", method = RequestMethod.GET)
    public ModelAndView managerContractAdd(@PathVariable("idPerson") Integer idPerson) throws DataExistenceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/contractEditAdd");
        modelAndView.addObject("idPerson",idPerson);
        modelAndView.addObject("contract", new Contract());

        return modelAndView;
    }

    @RequestMapping(value = "/ecare/manager/contract-{idContract}/extraoption-{idOption}-disable", method = RequestMethod.GET)
    public String managerTariffDisableOption(@PathVariable("idContract") Integer idContract,@PathVariable("idOption") Integer idOption) throws DataExistenceException {
        this.contractService.removeExtraOptionFromContract(idContract,idOption);

        return "redirect:/ecare/manager/contract-"+idContract+"-more";
    }


}
