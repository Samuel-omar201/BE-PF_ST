package com.umg.web.controller;

import com.umg.data.bo.TtCliente;
import com.umg.service.TtClienteService;
import com.umg.data.repository.TtClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/Autex_M1/ttCliente")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Manejo de clientes", protocols = "http")
public class TtClienteController {

    @Autowired
    private TtClienteRepository repository;

    @Autowired
    private TtClienteService service;

    @ApiOperation("Obtiene todos los clientes")
    @GetMapping("/getAll")
    ResponseEntity<List<TtCliente>> getAll() {
        List<TtCliente> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Obtiene un cliente por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<TtCliente> getById(@PathVariable Integer id) {
        Optional<TtCliente> cliente = this.service.findById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation("Agrega un nuevo cliente")
    @PostMapping("/save")
    ResponseEntity<TtCliente> save(@RequestBody TtCliente entity) {
        TtCliente response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un cliente")
    @PutMapping("/update")
    ResponseEntity<TtCliente> update(@RequestBody TtCliente entity) {
        TtCliente response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un cliente")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
