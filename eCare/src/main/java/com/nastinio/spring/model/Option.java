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
    /*@NotNull(message = "Введите логин")
    @Pattern(regexp = "^\\d+$", message = "Неверный формат логина")*/
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
}