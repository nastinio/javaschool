package com.nastinio.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "option_contract")
public class OptionContract implements Serializable {

    @EmbeddedId
    private OptionContractId id;

    public OptionContract() {
    }

    public OptionContractId getId() {
        return id;
    }

    public void setId(OptionContractId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OptionContract{" +
                "id=" + id.toString() +
                '}';
    }

    /*@Id
    @Column(name = "id_option")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idOption;

    @Id
    @Column(name = "id_contract")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idContract;*/



    /*public OptionContract(Integer idOption, Integer idContract) {
        this.idContract = idContract;
        this.idOption = idOption;
    }

    public Integer getIdOption() {
        return idOption;
    }

    public void setIdOption(Integer idOption) {
        this.idOption = idOption;
    }

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }*/
}
