package com.nastinio.spring.service;

import com.nastinio.spring.dao.BasketDAO;
import com.nastinio.spring.exceptions.DataExistenceException;
import com.nastinio.spring.model.Basket;
import com.nastinio.spring.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasketService {
    @Autowired
    BasketDAO basketDAO;

    @Autowired
    ContractService contractService;


//    public Boolean wasTariffChanged(Basket basket){
//        return basket.getContract().getTariffInContract().getId()==basket.getTariffInBasket().getId();
//    }



    public Basket getById(Integer id) throws DataExistenceException {
        return (Basket) this.basketDAO.getById(id);
    }

    public void add(Basket basket) {
        this.basketDAO.add(basket);
    }

    public void update(Basket basket) {
        this.basketDAO.update(basket);
    }

    public List<Basket> getList() {
        return this.basketDAO.getList();
    }

    public void remove(Integer id) {
        this.basketDAO.remove(id);
    }

    public void reset(Integer idContract) throws DataExistenceException {
        Contract contract = this.contractService.getById(idContract);
        Basket basket = contract.getBasket();
        basket.setTariffInBasket(contract.getTariffInContract());
        this.update(basket);

    }
}
