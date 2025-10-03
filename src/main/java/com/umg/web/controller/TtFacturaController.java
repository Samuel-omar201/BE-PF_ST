package com.umg.web.controller;

import com.umg.data.bo.TtFactura;
import com.umg.service.TtFacturaService;
import com.umg.data.repository.TtFacturaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttFactura")
@Api(value = "Manejo de facturas", protocols = "http")
public class TtFacturaController {

    @Autowired
    private TtFacturaRepository repository;

    @Autowired
    private TtFacturaService service;

    @ApiOperation("Obtiene todas las facturas")
    @GetMapping("/getAll")
    ResponseEntity<List<TtFactura>> getAll() {
        List<TtFactura> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva factura")
    @PostMapping("/save")
    ResponseEntity<TtFactura> save(@RequestBody TtFactura entity) {
        TtFactura response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza una factura")
    @PutMapping("/update")
    ResponseEntity<TtFactura> update(@RequestBody TtFactura entity) {
        TtFactura response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina una factura")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
