package com.ecommerce.demo.util.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractJpaDAO<T extends Serializable> implements GenericDAO<T> {
    private Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    protected AbstractJpaDAO(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    @Override
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T read(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void update(T entity) {

    }

    @Override
    @Transactional
    public void delete(Long id) {

    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }
}
