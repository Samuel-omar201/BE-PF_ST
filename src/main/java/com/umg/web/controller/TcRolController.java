package com.umg.web.controller;

import com.umg.data.bo.TcRol;
import com.umg.data.repository.TcRolRepository;
import com.umg.service.TcRolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/rol")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de roles", protocols = "http")
public class TcRolController {

    @Autowired
    private TcRolRepository repository;

    @Autowired
    private TcRolService service;

    @ApiOperation("Obtiene todos los roles")
    @GetMapping("/getAll")
    ResponseEntity<List<TcRol>> getAll() {
        List<TcRol> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo rol")
    @PostMapping("/save")
    ResponseEntity<TcRol> save(@RequestBody TcRol rol) {
        TcRol response = this.service.update(rol);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza rol")
    @PutMapping("/update")
    ResponseEntity<TcRol> update(@RequestBody TcRol rol) {
        TcRol response = this.service.update(rol);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina rol")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
