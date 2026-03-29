package com.example.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    void persist(T entity);
    void modify(T entity);
    Optional<T> fetchById(ID id);
    List<T> fetchAll();
    void removeById(ID id);
    void removeEntity(T entity);
}