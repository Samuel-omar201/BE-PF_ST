package com.umg.web.controller;

import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import com.umg.service.TtOrdenTrabajoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/orden-trabajo")
@Api(value = "Manejo de órdenes de trabajo", protocols = "http")
public class TtOrdenTrabajoController {

    @Autowired
    private TtOrdenTrabajoRepository repository;

    @Autowired
    private TtOrdenTrabajoService service;

    @ApiOperation("Obtiene todas las órdenes de trabajo")
    @GetMapping("/getAll")
    ResponseEntity<List<TtOrdenTrabajo>> getAll() {
        List<TtOrdenTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva orden de trabajo")
    @PostMapping("/save")
    ResponseEntity<TtOrdenTrabajo> save(@RequestBody TtOrdenTrabajo orden) {
        TtOrdenTrabajo response = this.service.update(orden);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza orden de trabajo")
    @PutMapping("/update")
    ResponseEntity<TtOrdenTrabajo> update(@RequestBody TtOrdenTrabajo orden) {
        TtOrdenTrabajo response = this.service.update(orden);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina orden de trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
