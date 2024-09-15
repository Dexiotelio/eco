package com.ecommerce.demo.controller;

import com.ecommerce.demo.services.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    private final ClientServices clientServices;

    @Autowired
    private ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

}
