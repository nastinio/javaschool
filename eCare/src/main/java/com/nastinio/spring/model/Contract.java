package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @Column(name = "id_contract")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer id_person;
    private int id_tariff;
    private String number;


    public int isBlockedByPerson;
    public int isBlockedByManager;


    @Transient
    public Tariff tariff;

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
/*  @ManyToOne
    @JoinColumn(name = "id")
    private Person person;*/

    @ManyToMany(mappedBy = "contractSet",fetch = FetchType.EAGER)
    private Set<Option> optionExtraSet = new HashSet<>();

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public int getId_tariff() {
        return id_tariff;
    }

    public void setId_tariff(int id_tariff) {
        this.id_tariff = id_tariff;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public int getIsBlockedByPerson() {
        return isBlockedByPerson;
    }

    public void setIsBlockedByPerson(int isBlockedByPerson) {
        this.isBlockedByPerson = isBlockedByPerson;
    }

    public int getIsBlockedByManager() {
        return isBlockedByManager;
    }

    public void setIsBlockedByManager(int isBlockedByManager) {
        this.isBlockedByManager = isBlockedByManager;
    }

    public Set<Option> getOptionExtraSet() {
        return optionExtraSet;
    }

    public void setOptionExtraSet(Set<Option> optionExtraSet) {
        this.optionExtraSet = optionExtraSet;
    }

    @Override
    public String toString(){
        return "Contract: id = "+id+", number = "+number+", id_tariff = "+id_tariff+", id_person = "+id_person+", isBlocked: byPerson = " + isBlockedByPerson+", byManager = "+isBlockedByManager;
    }
}
