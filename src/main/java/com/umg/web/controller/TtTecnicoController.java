package com.umg.web.controller;

import com.umg.data.bo.TtTecnico;
import com.umg.service.TtTecnicoService;
import com.umg.data.repository.TtTecnicoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttTecnico")
@Api(value = "Manejo de técnicos", protocols = "http")
public class TtTecnicoController {

    @Autowired
    private TtTecnicoRepository repository;

    @Autowired
    private TtTecnicoService service;

    @ApiOperation("Obtiene todos los técnicos")
    @GetMapping("/getAll")
    ResponseEntity<List<TtTecnico>> getAll() {
        List<TtTecnico> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo técnico")
    @PostMapping("/save")
    ResponseEntity<TtTecnico> save(@RequestBody TtTecnico entity) {
        TtTecnico response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un técnico")
    @PutMapping("/update")
    ResponseEntity<TtTecnico> update(@RequestBody TtTecnico entity) {
        TtTecnico response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un técnico")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
