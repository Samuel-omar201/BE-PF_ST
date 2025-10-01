package com.umg.web.controller;

import com.umg.data.bo.TcEstadoSeguimientoTrabajo;
import com.umg.data.repository.TcEstadoSeguimientoTrabajoRepository;
import com.umg.service.TcEstadoSeguimientoTrabajoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/estado-seguimiento")
@Api(value = "Manejo de estados de seguimiento de trabajo", protocols = "http")
public class TcEstadoSeguimientoTrabajoController {

    @Autowired
    private TcEstadoSeguimientoTrabajoRepository repository;

    @Autowired
    private TcEstadoSeguimientoTrabajoService service;

    @ApiOperation("Obtiene todos los estados de seguimiento")
    @GetMapping("/getAll")
    ResponseEntity<List<TcEstadoSeguimientoTrabajo>> getAll() {
        List<TcEstadoSeguimientoTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo estado de seguimiento")
    @PostMapping("/save")
    ResponseEntity<TcEstadoSeguimientoTrabajo> save(@RequestBody TcEstadoSeguimientoTrabajo estado) {
        TcEstadoSeguimientoTrabajo response = this.service.update(estado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza estado de seguimiento")
    @PutMapping("/update")
    ResponseEntity<TcEstadoSeguimientoTrabajo> update(@RequestBody TcEstadoSeguimientoTrabajo estado) {
        TcEstadoSeguimientoTrabajo response = this.service.update(estado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina estado de seguimiento")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
