package com.ecommerce.demo.util.DAO;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDAO {
    protected GenericJpaDao(Class<T> entityClass) {
        super(entityClass);
    }
}
