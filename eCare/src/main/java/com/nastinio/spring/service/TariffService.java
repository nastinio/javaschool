package com.nastinio.spring.service;

import com.nastinio.spring.dao.TariffDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.OptionCellular;
import com.nastinio.spring.model.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Service("tariffService")
public class TariffService {
    @Autowired
    TariffDAO tariffDAO;

    @Autowired
    OptionCellularService optionCellularService;

    public Tariff getById(Integer id) throws DataExistenceException {
        return this.tariffDAO.getById(id);
    }

    public void add(Tariff tariff) {
        this.tariffDAO.add(tariff);
    }

    public void update(Tariff tariff) {
        this.tariffDAO.update(tariff);
    }

    public List<Tariff> getList() {
        return this.tariffDAO.getList();
    }

    public void remove(Integer id) {
        this.tariffDAO.remove(id);
    }

    public void removeOptionFromTariff(Integer idTariff, Integer idOption) throws DataExistenceException {
        Tariff tariff = this.tariffDAO.getById(idTariff);
        Set<OptionCellular> optionSet = tariff.getOptionsOnTariff();

        OptionCellular optionForRemove = new OptionCellular();

        for(OptionCellular option: optionSet){
            if(option.getId()==idOption){
                optionForRemove = option;
                break;
            }
        }

        if(optionForRemove.getId()!=null){
            optionSet.remove(optionForRemove);
            this.update(tariff);
        }else{
            //TODO: пробросить исключение
        }

    }

    public void includeOptionInTariff(Integer idTariff, Integer idOption) throws DataExistenceException {
        Tariff tariff = this.tariffDAO.getById(idTariff);
        OptionCellular optionForInclude = this.optionCellularService.getById(idOption);

        Set<OptionCellular> listOptionInTariff = tariff.getOptionsOnTariff();
        if(!listOptionInTariff.contains(optionForInclude)){
            listOptionInTariff.add(optionForInclude);
            this.tariffDAO.update(tariff);
        }else{
            //TODO: обработчик, если опция в тарифе уже есть
            System.out.println("Опция в тарифе уже есть");
        }

    }

    /*public List<OptionCellular> getListAvailableOptionsForAdd(Integer idTariff) throws DataExistenceException {
        Tariff tariff = this.tariffDAO.getById(idTariff);
        Set<OptionCellular> optionSet = tariff.getOptionsOnTariff();

        List<OptionCellular> listAllOptions = this.optionCellularService.getList();
        for (OptionCellular option:listAllOptions){

        }

    }*/



}
