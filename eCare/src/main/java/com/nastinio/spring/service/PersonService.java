package com.nastinio.spring.service;


import java.util.List;
import com.nastinio.spring.model.Person;

//Here are our service classes that are using Hibernate DAO classes to work with Person objects.
public interface PersonService {

    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(int id);
    public void removePerson(int id);

}
