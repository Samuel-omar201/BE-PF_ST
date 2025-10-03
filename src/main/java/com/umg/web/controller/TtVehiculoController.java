package com.umg.web.controller;

import com.umg.data.bo.TtVehiculo;
import com.umg.service.TtVehiculoService;
import com.umg.data.repository.TtVehiculoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttVehiculo")
@Api(value = "Manejo de vehículos", protocols = "http")
public class TtVehiculoController {

    @Autowired
    private TtVehiculoRepository repository;

    @Autowired
    private TtVehiculoService service;

    @ApiOperation("Obtiene todos los vehículos")
    @GetMapping("/getAll")
    ResponseEntity<List<TtVehiculo>> getAll() {
        List<TtVehiculo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo vehículo")
    @PostMapping("/save")
    ResponseEntity<TtVehiculo> save(@RequestBody TtVehiculo entity) {
        TtVehiculo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un vehículo")
    @PutMapping("/update")
    ResponseEntity<TtVehiculo> update(@RequestBody TtVehiculo entity) {
        TtVehiculo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un vehículo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
