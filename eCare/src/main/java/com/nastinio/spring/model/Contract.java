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

    @Column(name = "id_person")
    private Integer id_person;

    @Column(name = "id_tariff")
    private Integer id_tariff;

    @Column(name = "is_blocked_person")
    public int isBlockedByPerson;

    @Column(name = "is_blocked_manager")
    public int isBlockedByManager;

    @ManyToOne
    @JoinColumn(name="id_tariff_contract", nullable=false)
    private Tariff tariffInContract;

    @ManyToOne
    @JoinColumn(name="id_person_contract", nullable=false)
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
                ", score=" + score +
                ", id_person=" + id_person +
                ", id_tariff=" + id_tariff +
                ", isBlockedByPerson=" + isBlockedByPerson +
                ", isBlockedByManager=" + isBlockedByManager +
                ", tariffInContract=" + tariffInContract +
                ", optionsOnContract=" + optionsOnContract +
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

    public Integer getId_person() {
        return id_person;
    }

    public void setId_person(Integer id_person) {
        this.id_person = id_person;
    }

    public Integer getId_tariff() {
        return id_tariff;
    }

    public void setId_tariff(Integer id_tariff) {
        this.id_tariff = id_tariff;
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
}
