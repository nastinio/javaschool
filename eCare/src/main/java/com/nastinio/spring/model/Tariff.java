package com.nastinio.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @Column(name = "id_tariff")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

   /* @OneToMany(mappedBy="option")
    private Set<Option> optionSet;*/

    @ManyToMany(mappedBy = "tariffSet",fetch = FetchType.EAGER)
    private Set<Option> optionSet = new HashSet<>();

    @Transient
    public List<Option> optionList;

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

    public Set<Option> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Option> optionSet) {
        this.optionSet = optionSet;
    }


    /*public Set<Option> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Option> optionSet) {
        this.optionSet = optionSet;
    }*/


    public Tariff() {

    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}