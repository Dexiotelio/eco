package com.ecommerce.demo.services;

import com.ecommerce.demo.util.DAO.GenericJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.demo.DAO.GenericDAOImpl;
import com.ecommerce.demo.entities.Client;

@Service
public class ClientServices {
    private final GenericJpaDao genericJpaDao;

    @Autowired
    private ClientServices(GenericJpaDao genericJpaDao) {
        this.genericJpaDao = genericJpaDao;
    }

    public void createClient(Client client) {
        genericJpaDao.save(client);
    }
}
