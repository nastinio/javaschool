/*
package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "option_cellular")
public class Option {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
/*@NotNull(message = "Введите логин")
    @Pattern(regexp = "^\\d+$", message = "Неверный формат логина")*//*

    public Integer id;

    private String name;
    private Float connectionCostOption;
    private Float costOption;

    @Transient
    private OptionRules rule;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "options_jointly",
            joinColumns = { @JoinColumn(name = "op1_id") },
            inverseJoinColumns = { @JoinColumn(name = "op2_id") }
    )
    Set<Option> jointlyOptions = new HashSet<>();


    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "options_exclude",
            joinColumns = { @JoinColumn(name = "op1_exclude_id") },
            inverseJoinColumns = { @JoinColumn(name = "op2_exclude_id") }
    )
    Set<Option> excludeOptions = new HashSet<>();


    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_tariff",
            joinColumns = { @JoinColumn(name = "id_option") },
            inverseJoinColumns = { @JoinColumn(name = "id_tariff") }
    )
    Set<Tariff> tariffSet = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_contract",
            joinColumns = { @JoinColumn(name = "id_option") },
            inverseJoinColumns = { @JoinColumn(name = "id_contract") }
    )
    Set<Tariff> contractSet = new HashSet<>();

    public Set<Tariff> getTariffSet() {
        return tariffSet;
    }

    public void setTariffSet(Set<Tariff> tariffSet) {
        this.tariffSet = tariffSet;
    }


    */
/* @ManyToOne
    @JoinColumn(name="id_tariff", nullable=false)
    private Tariff tariff;*//*




    @Override
    public String toString() {
        return "id = " + id + ", name = " + name + ", costConnection = " + connectionCostOption + ", cost = " + costOption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCostConnection() {
        return connectionCostOption;
    }

    public void setCostConnection(Float connectionCostOption) {
        this.connectionCostOption = connectionCostOption;
    }

    public Float getCost() {
        return costOption;
    }

    public void setCost(Float costOption) {
        this.costOption = costOption;
    }

    public Set<Option> getJointlyOptions() {
        return jointlyOptions;
    }

    public void setJointlyOptions(Set<Option> jointlyOptions) {
        this.jointlyOptions = jointlyOptions;
    }

    public Set<Option> getExcludeOptions() {
        return excludeOptions;
    }

    public void setExcludeOptions(Set<Option> excludeOptions) {
        this.excludeOptions = excludeOptions;
    }

    public OptionRules getRule() {
        return rule;
    }

    public void setRule(OptionRules rule) {
        this.rule = rule;
    }

    public Set<Tariff> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Tariff> contractSet) {
        this.contractSet = contractSet;
    }
}*/
