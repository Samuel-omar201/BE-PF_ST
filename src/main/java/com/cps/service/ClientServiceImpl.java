package com.cps.service;

import com.cps.data.bo.Client;
import com.cps.data.bo.Country;
import com.cps.data.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> findAll() {
        List<Client> response = repository.findAll();
        return response;
    }
}

