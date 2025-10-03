package com.umg.web.controller;

import com.umg.data.bo.TtAsignaRolUsuario;
import com.umg.service.TtAsignaRolUsuarioService;
import com.umg.data.repository.TtAsignaRolUsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttAsignaRolUsuario")
@Api(value = "Manejo de asignación de roles a usuarios", protocols = "http")
public class TtAsignaRolUsuarioController {

    @Autowired
    private TtAsignaRolUsuarioRepository repository;

    @Autowired
    private TtAsignaRolUsuarioService service;

    @ApiOperation("Obtiene todas las asignaciones de roles a usuarios")
    @GetMapping("/getAll")
    ResponseEntity<List<TtAsignaRolUsuario>> getAll() {
        List<TtAsignaRolUsuario> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva asignación de rol a usuario")
    @PostMapping("/save")
    ResponseEntity<TtAsignaRolUsuario> save(@RequestBody TtAsignaRolUsuario entity) {
        TtAsignaRolUsuario response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza una asignación de rol a usuario")
    @PutMapping("/update")
    ResponseEntity<TtAsignaRolUsuario> update(@RequestBody TtAsignaRolUsuario entity) {
        TtAsignaRolUsuario response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina una asignación de rol a usuario")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
