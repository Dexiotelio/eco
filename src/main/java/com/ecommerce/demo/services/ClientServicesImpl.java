package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.dto.response.ClientResponse;
import com.ecommerce.demo.entities.Client;
import com.ecommerce.demo.services.services.ClientServices;
import com.ecommerce.demo.util.dao.GenericJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientServicesImpl implements ClientServices {
    private final GenericJpaDao<Client> genericJpaDao;

    @Autowired
    public ClientServicesImpl(GenericJpaDao<Client> genericJpaDao) {
        this.genericJpaDao = genericJpaDao;
    }

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        final Optional<Client> clientExists = genericJpaDao.findById(request.getId());
        if (clientExists.isPresent()) {
            return null;
        }
        final Client client = Client.toClientResponse(request);
        genericJpaDao.save(client);

        final Optional<Client> persistentClient = genericJpaDao.findById(client.getId());
        if (persistentClient.isEmpty()) {
            return null;
        }
        // si el client ha sido persistido
        Client clientCreated = persistentClient.get();
        return ClientResponse.toClientResponse(clientCreated);
    }

    @Override
    public ClientResponse readClient(ClientRequest request) {
        return null;
    }
}
