/*
package com.nastinio.spring.service;

import com.nastinio.spring.dao.ContractDAO;
import com.nastinio.spring.dao.TariffDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Contract;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ecareService")
public class EcareService {

    @Autowired
    ContractService contractService;

    @Autowired
    TariffDAO tariffDAO;

    @Autowired
    TariffService tariffService;

    @Transactional
    public List<Contract> listByIdPerson(Integer idPerson) {
        */
/*
         * Список контрактов, подключенных к пользователю
         * *//*

        List<Contract> allContractList = this.contractService.list();
        List<Contract> contractByPersonId = new ArrayList<>();

        for (Contract contract : allContractList) {
            if (contract.getId_person() == idPerson) {
                contractByPersonId.add(contract);
            }
        }

        return contractByPersonId;
    }

    private List<Contract> setTariiffForListContract(List<Contract> contractList) throws DataExistenceException {
        */
/*
         * Подцепляет тарифы к каждому контракту в списке
         * *//*

        for (Contract contract : contractList) {
            Tariff tariff = this.tariffDAO.getById(contract.getId_tariff());
            contract.setTariff(tariff);
            System.out.println(contract.toString());
        }
        return contractList;
    }

    public List<Contract> getContractWithTariffList(Integer idPerson) throws DataExistenceException {
        */
/*
         * Возвращает список контрактов для пользователя с подцепленными тарифами
         * *//*

        return setTariiffForListContract(listByIdPerson(idPerson));
    }

    public List<Contract> getContractWithTariffAndOptionsList(Integer idPerson) throws DataExistenceException {
        */
/*
         * Возвращает список контрактов для пользователя с подцепленными тарифами
         * к каждому тарифу подцепляет set опций
         * *//*

        return setTariiffForListContract(listByIdPerson(idPerson));
    }

    public Contract getTariffForContractByIdContract(Integer idContract) throws DataExistenceException {
        */
/*
         * Подцепит к контракту тариф
         * *//*

        Contract contract = (Contract) this.contractService.getById(idContract);
        Tariff tariff = this.tariffDAO.getById(contract.getId_tariff());
        contract.setTariff(tariff);
        return contract;
    }

    public void changeTariffIntoContract(Integer idContract, Integer idTariff) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        System.out.println("Контракт со страрым    тарифом: " + contract.getId_tariff());
        contract.setId_tariff(idTariff);
        System.out.println("Контракт с обновленным тарифом: " + contract.getId_tariff());
        this.contractService.updateContract(contract);
    }


}
*/
