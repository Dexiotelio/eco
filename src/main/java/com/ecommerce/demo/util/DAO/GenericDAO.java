package com.ecommerce.demo.util.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    void save(T entity);
    T read(Long id);
    void update(T entity);
    void delete (Long id);
    Optional<T> findById(Long id);
    List<T> findAll();
}
