package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.dto.response.ClientResponse;
import com.ecommerce.demo.services.ClientServicesImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client")
public class ClientController {
    private ClientServicesImpl clientServices;

    @Autowired
    private ClientController(ClientServicesImpl clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequest request) {
        ClientResponse response = clientServices.createClient(request);
    }

    @GetMapping
    public ResponseEntity<?> getClients(ClientRequest request) {
        return null;
    }
}
