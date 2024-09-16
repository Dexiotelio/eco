package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.request.ClientRequest;
import com.ecommerce.demo.services.ClientServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client")
public class ClientController {
    private ClientServicesImpl clientServices;

    @Autowired
    private ClientController(ClientServicesImpl clientServices) {
        this.clientServices = clientServices;
    }

    @GetMapping
    public ResponseEntity<?> getClients(ClientRequest request) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(ClientRequest request) {
        return null;
    }
}
