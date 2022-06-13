package com.cps.web.controller;

import com.cps.data.bo.Country;
import com.cps.data.repository.CountryRepository;
import com.cps.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/quoter/country")
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @Autowired
    private CountryService service;

    @GetMapping("/getAll")
    ResponseEntity<List<Country>> getAll() {
        List<Country> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    ResponseEntity<Country> save(@RequestBody Country country) {
        Country response = this.repository.save(country);
        return new ResponseEntity<Country>(response, HttpStatus.OK);
    }
}
