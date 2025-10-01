package com.umg.web.controller;


import com.umg.data.bo.Car;
import com.umg.data.repository.CarRepository;
import com.umg.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/rentaAutos/car")
@Api(value = "Manejo de vehiculos", protocols = "http")
public class CarController {

    @Autowired
    private CarRepository repository;

    @Autowired
    private CarService service;

    @ApiOperation("Obtiene todos los vehículos")
    @GetMapping("/getAll")
    ResponseEntity<List<Car>> getAll() {
        List<Car> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo vehiculo")
    @PostMapping("/save")
    ResponseEntity<Car> save(@RequestBody Car car) {
        Car response = this.service.update(car);
        return new ResponseEntity<Car>(response, HttpStatus.OK);
    }

    @ApiOperation("actualiza vehículo")
    @PutMapping("/update")
    ResponseEntity<Car> update(@RequestBody Car car) {
        Car response = this.service.update(car);
        return new ResponseEntity<Car>(response, HttpStatus.OK);
    }

    @ApiOperation("elimina vehiculo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
