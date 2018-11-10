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
    ContractDAO contractDAO;

    @Autowired
    TariffDAO tariffDAO;

    @Transactional
    public List<Contract> listByIdPerson(Integer idPerson) {
        /*
        * Список контрактов, подключенных к пользователю
        * */
        List<Contract> allContractList = this.contractDAO.getList();
        List<Contract> contractByPersonId = new ArrayList<>();

        for (Contract contract : allContractList) {
            if (contract.getId_person() == idPerson) {
                contractByPersonId.add(contract);
            }
        }

        return contractByPersonId;
    }

    private List<Contract> setTariiffForListContract(List<Contract> contractList) throws DataExistenceException {
        /*
        * Подцепляет тарифы к каждому контракту в списке
        * */
        for (Contract contract : contractList) {
            Tariff tariff = this.tariffDAO.getById(contract.getId_tariff());
            contract.setTariff(tariff);
            System.out.println(contract.toString());
        }
        return contractList;
    }

    public List<Contract> getContractWithTariffList(Integer idPerson) throws DataExistenceException {
        /*
        * Возвращает список контрактов для пользователя с подцепленными тарифами
        * */
        return setTariiffForListContract(listByIdPerson(idPerson));
    }



}
