package com.umg.web.controller;

import com.umg.data.bo.TtAsignaTecnicoTrabajo;
import com.umg.service.TtAsignaTecnicoTrabajoService;
import com.umg.data.repository.TtAsignaTecnicoTrabajoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttAsignaTecnicoTrabajo")
@Api(value = "Manejo de asignación de técnicos a trabajos", protocols = "http")
public class TtAsignaTecnicoTrabajoController {

    @Autowired
    private TtAsignaTecnicoTrabajoRepository repository;

    @Autowired
    private TtAsignaTecnicoTrabajoService service;

    @ApiOperation("Obtiene todas las asignaciones de técnicos a trabajos")
    @GetMapping("/getAll")
    ResponseEntity<List<TtAsignaTecnicoTrabajo>> getAll() {
        List<TtAsignaTecnicoTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva asignación de técnico a trabajo")
    @PostMapping("/save")
    ResponseEntity<TtAsignaTecnicoTrabajo> save(@RequestBody TtAsignaTecnicoTrabajo entity) {
        TtAsignaTecnicoTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza una asignación de técnico a trabajo")
    @PutMapping("/update")
    ResponseEntity<TtAsignaTecnicoTrabajo> update(@RequestBody TtAsignaTecnicoTrabajo entity) {
        TtAsignaTecnicoTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina una asignación de técnico a trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
