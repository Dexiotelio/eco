package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.dto.response.ClientResponse;
import com.ecommerce.demo.services.services.ClientServices;
import com.ecommerce.demo.util.dao.GenericJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServicesImpl implements ClientServices {
    private final GenericJpaDao<ClientRequest> genericJpaDao;

    @Autowired
    public ClientServicesImpl(GenericJpaDao<ClientRequest> genericJpaDao) {
        this.genericJpaDao = genericJpaDao;
    }

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        return null;
    }

    @Override
    public ClientResponse readClient(ClientRequest request) {
        return null;
    }
}
