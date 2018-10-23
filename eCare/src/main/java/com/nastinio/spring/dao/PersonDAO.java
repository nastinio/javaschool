package com.nastinio.spring.dao;

import java.util.List;

import com.nastinio.spring.model.Person;

public interface PersonDAO {

    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(String id);
    public void removePerson(String id);
}
