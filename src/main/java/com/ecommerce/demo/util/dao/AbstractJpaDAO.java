package com.ecommerce.demo.util.dao;

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
        return entityManager.find(entityClass, id);
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }
}
