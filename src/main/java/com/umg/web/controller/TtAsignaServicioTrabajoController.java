package com.umg.web.controller;

import com.umg.data.bo.TtAsignaServicioTrabajo;
import com.umg.service.TtAsignaServicioTrabajoService;
import com.umg.data.repository.TtAsignaServicioTrabajoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttAsignaServicioTrabajo")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de asignación de servicios a trabajos", protocols = "http")
public class TtAsignaServicioTrabajoController {

    @Autowired
    private TtAsignaServicioTrabajoRepository repository;

    @Autowired
    private TtAsignaServicioTrabajoService service;

    @ApiOperation("Obtiene todas las asignaciones de servicios a trabajos")
    @GetMapping("/getAll")
    ResponseEntity<List<TtAsignaServicioTrabajo>> getAll() {
        List<TtAsignaServicioTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva asignación de servicio a trabajo")
    @PostMapping("/save")
    ResponseEntity<TtAsignaServicioTrabajo> save(@RequestBody TtAsignaServicioTrabajo entity) {
        TtAsignaServicioTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza una asignación de servicio a trabajo")
    @PutMapping("/update")
    ResponseEntity<TtAsignaServicioTrabajo> update(@RequestBody TtAsignaServicioTrabajo entity) {
        TtAsignaServicioTrabajo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina una asignación de servicio a trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
