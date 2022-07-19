package com.exadel.finance.manager.service;

import java.util.List;

public interface GenericService<T> {
    T saveOrUpdate(T entity);

    T findById(Long id);

    void deleteById(Long id);

    List<T> findAll();

    List<T> findAll(String searchSpecification);
}
