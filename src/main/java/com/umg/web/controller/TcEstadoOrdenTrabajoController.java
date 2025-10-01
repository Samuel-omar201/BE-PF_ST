package com.umg.web.controller;

import com.umg.data.bo.TcEstadoOrdenTrabajo;
import com.umg.data.repository.TcEstadoOrdenTrabajoRepository;
import com.umg.service.TcEstadoOrdenTrabajoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/estado-orden")
@Api(value = "Manejo de estados de orden de trabajo", protocols = "http")
public class TcEstadoOrdenTrabajoController {

    @Autowired
    private TcEstadoOrdenTrabajoRepository repository;

    @Autowired
    private TcEstadoOrdenTrabajoService service;

    @ApiOperation("Obtiene todos los estados de orden de trabajo")
    @GetMapping("/getAll")
    ResponseEntity<List<TcEstadoOrdenTrabajo>> getAll() {
        List<TcEstadoOrdenTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo estado de orden de trabajo")
    @PostMapping("/save")
    ResponseEntity<TcEstadoOrdenTrabajo> save(@RequestBody TcEstadoOrdenTrabajo estado) {
        TcEstadoOrdenTrabajo response = this.service.update(estado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza estado de orden de trabajo")
    @PutMapping("/update")
    ResponseEntity<TcEstadoOrdenTrabajo> update(@RequestBody TcEstadoOrdenTrabajo estado) {
        TcEstadoOrdenTrabajo response = this.service.update(estado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina estado de orden de trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
