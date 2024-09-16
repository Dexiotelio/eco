package com.ecommerce.demo.services.services;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.dto.response.ClientResponse;

public interface ClientServices {
    public ClientResponse createClient(ClientRequest request);
    public ClientResponse readClient(ClientRequest request);
}
