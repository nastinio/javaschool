package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @Column(name = "id_basket")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "basket")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name="id_tariff", nullable=false)
    private Tariff tariffInBasket;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "basket_option_add",
            joinColumns = {@JoinColumn(name = "id_basket_add")},
            inverseJoinColumns = {@JoinColumn(name = "id_option_add")}
    )
    private Set<OptionCellular> optionsForAdd = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "basket_option_remove",
            joinColumns = {@JoinColumn(name = "id_basket_remove")},
            inverseJoinColumns = {@JoinColumn(name = "id_option_remove")}
    )
    private Set<OptionCellular> optionsForRemove = new HashSet<>();

    public Basket() {
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tariff getTariffInBasket() {
        return tariffInBasket;
    }

    public void setTariffInBasket(Tariff tariffInBasket) {
        this.tariffInBasket = tariffInBasket;
    }

    public Set<OptionCellular> getOptionsForAdd() {
        return optionsForAdd;
    }

    public void setOptionsForAdd(Set<OptionCellular> optionsForAdd) {
        this.optionsForAdd = optionsForAdd;
    }

    public Set<OptionCellular> getOptionsForRemove() {
        return optionsForRemove;
    }

    public void setOptionsForRemove(Set<OptionCellular> optionsForRemove) {
        this.optionsForRemove = optionsForRemove;
    }
}
