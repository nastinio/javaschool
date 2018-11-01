package com.nastinio.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "option")
public class Option {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@NotNull(message = "Введите логин")
    @Pattern(regexp = "^\\d+$", message = "Неверный формат логина")*/
    public Integer id;

    //@Column//(name = "`name`")
    private String name;

    //@Column//(name = "`connection_cost_option`")
    private Float connectionCostOption;

    //@Column//(name = "`cost_option`")
    private Float costOption;

    @Override
    public String toString() {
        return "id = " + id + ", name = " + name;
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

    public Float getConnectionCostOption() {
        return connectionCostOption;
    }

    public void setConnectionCostOption(Float connectionCostOption) {
        this.connectionCostOption = connectionCostOption;
    }

    public Float getCostOption() {
        return costOption;
    }

    public void setCostOption(Float costOption) {
        this.costOption = costOption;
    }
}