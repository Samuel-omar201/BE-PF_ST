package com.cps.service;

import com.cps.data.bo.Country;
import com.cps.data.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Country> findAll() {
        List<Country> response = repository.findAll();
        return response;
    }
}

