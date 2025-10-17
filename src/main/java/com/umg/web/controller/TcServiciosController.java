package com.umg.web.controller;

import com.umg.data.bo.TcServicios;
import com.umg.service.TcServiciosService;
import com.umg.data.repository.TcServiciosRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/tcServicios")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de servicios", protocols = "http")
public class TcServiciosController {

    @Autowired
    private TcServiciosRepository repository;

    @Autowired
    private TcServiciosService service;

    @ApiOperation("Obtiene todos los servicios")
    @GetMapping("/getAll")
    ResponseEntity<List<TcServicios>> getAll() {
        List<TcServicios> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo servicio")
    @PostMapping("/save")
    ResponseEntity<TcServicios> save(@RequestBody TcServicios entity) {
        TcServicios response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un servicio")
    @PutMapping("/update")
    ResponseEntity<TcServicios> update(@RequestBody TcServicios entity) {
        TcServicios response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un servicio")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
