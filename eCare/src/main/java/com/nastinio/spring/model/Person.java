package com.nastinio.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EntityDAO bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Введите логин")
    @Pattern(regexp = "^\\d+$", message = "Неверный формат логина")
    public Integer id;

    private String password;

    private String firstname;
    private String lastname;
    private String dob;
    private String passport;
    private String address;
    private String email;

    @Transient
    private List<Contract> contractList;

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    /* @OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
    private Set<Contract> contractSet = new HashSet<>();*/


    public Integer isOnline;

    public boolean isOnline() {
        return isOnline == 1;
    }

    public void setOnline(boolean online) {
        if (online) {
            isOnline = 1;
        } else isOnline = 0;
    }

   /* public Set<Contract> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Contract> contractSet) {
        this.contractSet = contractSet;
    }*/

    //private HashMap<Integer,Contract>  listContracts;

    /*public Person(Integer id, String password) {
        this.id = id;
        this.password = password;
    }*/

    public Person() {
    }

    public Person(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id = " + id + ", password = " + password + ", name = " + firstname + " " + lastname + ", dob = " +
                dob + ", passport = " + passport + ", email = " + email + ", isOnline = " + isOnline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
