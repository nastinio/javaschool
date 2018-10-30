package com.nastinio.spring.dao;

import java.util.List;

public interface Crud<T> {

    public void add(T entity);

    public void update(T entity);

    public List<T> getList();

    public T getById(Integer id);

    public void remove(Integer id);
}
