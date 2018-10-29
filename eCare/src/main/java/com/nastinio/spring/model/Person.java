package com.nastinio.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

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
    public String id;

    private String password;
    private String firstname;
    private String lastname;
    private String dob;
    private String passport;
    private String address;
    private String email;

    //private HashMap<Integer,Contract>  listContracts;

    public Person(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Person(){}

    @Override
    public String toString() {
        return "id = " + id + ", password = " + password + ", name = " + firstname + " " + lastname + ", dob = " +
                dob + ", passport = " + passport + ", email = " + email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
