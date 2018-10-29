package com.nastinio.spring.dao;

import java.util.List;

public interface Crud<K, T> {

    public void add(T entity);

    public void update(T entity);

    public List<T> getList();

    public T getById(K id);

    public void remove(K id);
}
