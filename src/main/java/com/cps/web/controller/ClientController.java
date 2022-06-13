package com.cps.web.controller;

import com.cps.data.bo.Client;
import com.cps.data.repository.ClientRepository;
import com.cps.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/quoter/client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService service;

    @GetMapping("/getAll")
    ResponseEntity<List<Client>> getAll() {
        List<Client> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    ResponseEntity<Client> save(@RequestBody Client client) {
        Client response = this.repository.save(client);
        return new ResponseEntity<Client>(response, HttpStatus.OK);
    }

}
