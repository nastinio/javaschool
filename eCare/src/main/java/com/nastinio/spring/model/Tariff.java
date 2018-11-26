package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @Column(name = "id_tariff")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Integer cost;

    @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
    @JoinTable(
            name = "tariff_option",
            joinColumns = { @JoinColumn(name = "id_tariff") },
            inverseJoinColumns = { @JoinColumn(name = "id_option") }
    )
    Set<OptionCellular> optionsOnTariff = new HashSet<>();


    @OneToMany(mappedBy="tariffInContract")
    private Set<Contract> contracts;

    @OneToMany(mappedBy="tariffInContractForChange")
    private Set<Contract> contractsWithChangeTariff;


    public Tariff() {
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", optionsOnTariff=" + optionsOnTariff +
                '}';
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Set<OptionCellular> getOptionsOnTariff() {
        return optionsOnTariff;
    }

    public void setOptionsOnTariff(Set<OptionCellular> optionsOnTariff) {
        this.optionsOnTariff = optionsOnTariff;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(id, tariff.id) &&
                Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Set<Contract> getContractsWithChangeTariff() {
        return contractsWithChangeTariff;
    }

    public void setContractsWithChangeTariff(Set<Contract> contractsWithChangeTariff) {
        this.contractsWithChangeTariff = contractsWithChangeTariff;
    }
}