package com.cps.service;

import com.cps.data.bo.Region;
import com.cps.data.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RestTemplate restTemplate;

    private final RegionRepository repository;

    public RegionServiceImpl(RegionRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Region> findAll() {
        List<Region> response = repository.findAll();
        return response;
    }
}
