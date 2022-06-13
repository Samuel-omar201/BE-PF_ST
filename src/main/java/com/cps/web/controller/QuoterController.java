package com.cps.web.controller;

import com.cps.data.dto.PackageDTO;
import com.cps.data.repository.RegionRepository;
import com.cps.service.QuoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/quoter/quotation")
public class QuoterController {

    @Autowired
    private RegionRepository repository;

    @Autowired
    private QuoterService service;

    @PostMapping("/get")
    ResponseEntity<Float> getQuotation(@RequestBody PackageDTO dto) {
        Float response = this.service.getQuotation(dto);
        return new ResponseEntity<Float>(response, HttpStatus.OK);
    }
}
