package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @Column(name = "id_contract")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private String number;

    @Column(name = "score")
    private Integer score;

    @Column(name = "is_blocked_person")
    public Integer isBlockedByPerson;

    @Column(name = "is_blocked_manager")
    public Integer isBlockedByManager;

    @ManyToOne
    @JoinColumn(name="id_tariff", nullable=false)
    private Tariff tariffInContract;

    @Transient
    private Integer idTariff;

    @ManyToOne
    @JoinColumn(name="id_person", nullable=false)
    private Person personInContract;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_option",
            joinColumns = { @JoinColumn(name = "id_contract") },
            inverseJoinColumns = { @JoinColumn(name = "id_option") }
    )
    Set<OptionCellular> optionsOnContract = new HashSet<>();

    public Contract() {
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", options=" + optionsOnContract +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Tariff getTariffInContract() {
        return tariffInContract;
    }

    public void setTariffInContract(Tariff tariffInContract) {
        this.tariffInContract = tariffInContract;
    }

    public Set<OptionCellular> getOptionsOnContract() {
        return optionsOnContract;
    }

    public void setOptionsOnContract(Set<OptionCellular> optionsOnContract) {
        this.optionsOnContract = optionsOnContract;
    }

    public Person getPersonInContract() {
        return personInContract;
    }

    public void setPersonInContract(Person personInContract) {
        this.personInContract = personInContract;
    }

    public Integer getIsBlockedByPerson() {
        return isBlockedByPerson;
    }

    public void setIsBlockedByPerson(Integer isBlockedByPerson) {
        this.isBlockedByPerson = isBlockedByPerson;
    }

    public Integer getIsBlockedByManager() {
        return isBlockedByManager;
    }

    public void setIsBlockedByManager(Integer isBlockedByManager) {
        this.isBlockedByManager = isBlockedByManager;
    }

    public Integer getIdTariff() {
        return idTariff;
    }

    public void setIdTariff(Integer idTariff) {
        this.idTariff = idTariff;
    }
}
