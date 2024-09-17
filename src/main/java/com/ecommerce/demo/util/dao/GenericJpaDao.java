package com.ecommerce.demo.util.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDAO<T> {
    protected GenericJpaDao(Class<T> entityClass) {
        super(entityClass);
    }
}
