package com.cps.web.controller;

import com.cps.data.bo.Region;
import com.cps.data.repository.RegionRepository;
import com.cps.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/quoter/region")
public class RegionController {

    @Autowired
    private RegionRepository repository;

    @Autowired
    private RegionService service;

    @GetMapping("/getAll")
    ResponseEntity<List<Region>> getAll() {
        List<Region> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    ResponseEntity<Region> save(@RequestBody Region region) {
        Region response = this.repository.save(region);
        return new ResponseEntity<Region>(response, HttpStatus.OK);
    }

}
