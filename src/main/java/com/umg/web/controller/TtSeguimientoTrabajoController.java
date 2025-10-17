package com.umg.web.controller;

import com.umg.data.bo.TtSeguimientoTrabajo;
import com.umg.service.TtSeguimientoTrabajoService;
import com.umg.data.repository.TtSeguimientoTrabajoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttSeguimientoTrabajo")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de seguimientos de trabajos", protocols = "http")
public class TtSeguimientoTrabajoController {

    @Autowired
    private TtSeguimientoTrabajoRepository repository;

    @Autowired
    private TtSeguimientoTrabajoService service;

    @ApiOperation("Obtiene todos los seguimientos de trabajo")
    @GetMapping("/getAll")
    ResponseEntity<List<TtSeguimientoTrabajo>> getAll() {
        List<TtSeguimientoTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo seguimiento de trabajo")
    @PostMapping("/save")
    ResponseEntity<TtSeguimientoTrabajo> save(@RequestBody TtSeguimientoTrabajo entity) {
        TtSeguimientoTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un seguimiento de trabajo")
    @PutMapping("/update")
    ResponseEntity<TtSeguimientoTrabajo> update(@RequestBody TtSeguimientoTrabajo entity) {
        TtSeguimientoTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un seguimiento de trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
