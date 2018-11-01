package com.nastinio.spring.dao;

import com.nastinio.spring.exceptions.DataExistenceException;

import java.util.List;

public interface Crud<T> {

    public void add(T entity);

    public void update(T entity);

    public List<T> getList();

    public T getById(Integer id) throws DataExistenceException;

    public void remove(Integer id);
}
