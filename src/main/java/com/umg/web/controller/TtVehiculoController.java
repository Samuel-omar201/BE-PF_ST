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
import java.util.Optional;

@RestController
@RequestMapping("/service/Autex_M1/ttVehiculo")
@CrossOrigin(origins = "http://localhost:3000")
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

    @ApiOperation("Obtiene un vehículo por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<TtVehiculo> getById(@PathVariable Integer id) {
        Optional<TtVehiculo> vehiculo = this.service.findById(id);
        return vehiculo.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
