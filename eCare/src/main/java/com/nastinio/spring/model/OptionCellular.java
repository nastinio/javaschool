package com.nastinio.spring.model;

import com.nastinio.spring.enums.CorrelationType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "option_cellular")
public class OptionCellular {

    @Id
    @Column(name = "id_option")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private String name;

    @Column(name = "cost_connection")
    private Integer costConnection;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Integer cost;

    @Transient
    private CorrelationType correlation;

    @Transient
    private String include;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_jointly",
            joinColumns = { @JoinColumn(name = "id_option_jointly_1") },
            inverseJoinColumns = { @JoinColumn(name = "id_option_jointly_2") }
    )
    Set<OptionCellular> jointlyOptions = new HashSet<>();

/////////////////////////////////////////////////////////

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(name = "option_exclude",
            joinColumns = { @JoinColumn(name = "id_option1") },
            inverseJoinColumns = { @JoinColumn(name = "id_option2") }
    )
    Set<OptionCellular> excludeLeftOptions = new HashSet<>();

    /*Исключающие опции справа*/
    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_exclude",
            joinColumns = { @JoinColumn(name = "id_option2") },
            inverseJoinColumns = { @JoinColumn(name = "id_option1") }
    )
    Set<OptionCellular> excludeRightOptions = new HashSet<>();
/////////////////////////////////////////////////////////



    /*Список тарифов, в которые включена опция*/
    @ManyToMany(mappedBy = "optionsOnTariff")
    private Set<Tariff> tariffs = new HashSet<>();

    /*Список контрактов, в которые включена опция*/
    @ManyToMany(mappedBy = "optionsOnContract")
    private Set<Contract> contracts = new HashSet<>();

    @Override
    public String toString() {
        return "OptionCellular{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", cost=" + cost +
                ", costConnection=" + costConnection +
                //", jointlyOptions=" + jointlyOptions +
                /*", excludeLeftOptions=" + excludeLeftOptions +
                ", excludeRightOptions=" + excludeRightOptions +*/
                '}';
    }

    public OptionCellular() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionCellular that = (OptionCellular) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(costConnection, that.costConnection) &&
                Objects.equals(description, that.description) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, costConnection, description, cost);
    }

    public OptionCellular(Integer id){
        this.id = id;
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

    public Integer getCostConnection() {
        return costConnection;
    }

    public void setCostConnection(Integer costConnection) {
        this.costConnection = costConnection;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Set<OptionCellular> getExcludeLeftOptions() {
        return excludeLeftOptions;
    }

    public void setExcludeLeftOptions(Set<OptionCellular> excludeLeftOptions) {
        this.excludeLeftOptions = excludeLeftOptions;
    }

    public Set<OptionCellular> getJointlyOptions() {
        return jointlyOptions;
    }

    public void setJointlyOptions(Set<OptionCellular> jointlyOptions) {
        this.jointlyOptions = jointlyOptions;
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<OptionCellular> getExcludeRightOptions() {
        return excludeRightOptions;
    }

    public void setExcludeRightOptions(Set<OptionCellular> excludeRightOptions) {
        this.excludeRightOptions = excludeRightOptions;
    }

    public CorrelationType getCorrelation() {
        return correlation;
    }

    public void setCorrelation(CorrelationType correlation) {
        this.correlation = correlation;
    }


    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }
}