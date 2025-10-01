package com.umg.web.controller;

import com.umg.data.bo.TtLogs;
import com.umg.data.repository.TtLogsRepository;
import com.umg.service.TtLogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/Autex_M1/logs")
@Api(value = "Manejo de logs", protocols = "http")
public class TtLogsController {

    @Autowired
    private TtLogsRepository repository;

    @Autowired
    private TtLogsService service;

    @ApiOperation("Obtiene todos los logs")
    @GetMapping("/getAll")
    ResponseEntity<List<TtLogs>> getAll() {
        List<TtLogs> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo log")
    @PostMapping("/save")
    ResponseEntity<TtLogs> save(@RequestBody TtLogs ttLogs) {
        TtLogs response = this.service.update(ttLogs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza log")
    @PutMapping("/update")
    ResponseEntity<TtLogs> update(@RequestBody TtLogs ttLogs) {
        TtLogs response = this.service.update(ttLogs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina log")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
