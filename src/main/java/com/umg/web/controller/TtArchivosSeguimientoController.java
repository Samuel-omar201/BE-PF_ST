package com.umg.web.controller;

import com.umg.data.bo.TtArchivosSeguimiento;
import com.umg.service.TtArchivosSeguimientoService;
import com.umg.data.repository.TtArchivosSeguimientoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/ttArchivosSeguimiento")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de archivos de seguimiento", protocols = "http")
public class TtArchivosSeguimientoController {

    @Autowired
    private TtArchivosSeguimientoRepository repository;

    @Autowired
    private TtArchivosSeguimientoService service;

    @ApiOperation("Obtiene todos los archivos de seguimiento")
    @GetMapping("/getAll")
    ResponseEntity<List<TtArchivosSeguimiento>> getAll() {
        List<TtArchivosSeguimiento> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo archivo de seguimiento")
    @PostMapping("/save")
    ResponseEntity<TtArchivosSeguimiento> save(@RequestBody TtArchivosSeguimiento entity) {
        TtArchivosSeguimiento response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza un archivo de seguimiento")
    @PutMapping("/update")
    ResponseEntity<TtArchivosSeguimiento> update(@RequestBody TtArchivosSeguimiento entity) {
        TtArchivosSeguimiento response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un archivo de seguimiento")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
