package com.umg.web.controller;

import com.umg.data.bo.TtSeguimientoTrabajo;
import com.umg.service.TtSeguimientoTrabajoService;
import com.umg.data.repository.TtSeguimientoTrabajoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service/Autex_M1/ttSeguimientoTrabajo")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de seguimientos de trabajos", protocols = "http")
public class TtSeguimientoTrabajoController {

    @Autowired
    private TtSeguimientoTrabajoRepository repository;

    @Autowired
    private TtSeguimientoTrabajoService service;

    @ApiOperation("Obtiene todos los seguimientos de trabajo")
    @GetMapping("/getAll")
    ResponseEntity<List<TtSeguimientoTrabajo>> getAll() {
        List<TtSeguimientoTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega un nuevo seguimiento de trabajo")
    @PostMapping("/save")
    ResponseEntity<Map<String, Object>> save(@RequestBody TtSeguimientoTrabajo entity) {
        try {
            TtSeguimientoTrabajo saved = this.service.save(entity);

            // ✅ Construir respuesta sin relaciones lazy para evitar error de serialización
            Map<String, Object> seguimientoMap = new HashMap<>();
            seguimientoMap.put("idSeguimientoTrabajo", saved.getIdSeguimientoTrabajo());
            seguimientoMap.put("nombreSeguimiento", saved.getNombreSeguimiento());
            seguimientoMap.put("descripcionSeguimiento", saved.getDescripcionSeguimiento());
            seguimientoMap.put("notasTecnicas", saved.getNotasTecnicas());
            seguimientoMap.put("fechaRegistro", saved.getFechaRegistro());
            seguimientoMap.put("estadoRegistro", saved.getEstadoRegistro());
            seguimientoMap.put("ttOrdenTrabajoIdOrdenTrabajo", saved.getTtOrdenTrabajoIdOrdenTrabajo());
            seguimientoMap.put("tcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo", saved.getTcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo());

            // Agregar nombre del estado si existe
            if (saved.getEstadoSeguimiento() != null) {
                seguimientoMap.put("estadoNombre", saved.getEstadoSeguimiento().getNombreEstadoSeguimiento());
            }

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Seguimiento creado exitosamente");
            result.put("seguimiento", seguimientoMap);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Error al crear el seguimiento: " + e.getMessage());

            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Actualiza un seguimiento de trabajo")
    @PutMapping("/update")
    ResponseEntity<Map<String, Object>> update(@RequestBody TtSeguimientoTrabajo entity) {
        try {
            TtSeguimientoTrabajo updated = this.service.update(entity);

            // Construir respuesta sin relaciones lazy
            Map<String, Object> seguimientoMap = new HashMap<>();
            seguimientoMap.put("idSeguimientoTrabajo", updated.getIdSeguimientoTrabajo());
            seguimientoMap.put("nombreSeguimiento", updated.getNombreSeguimiento());
            seguimientoMap.put("descripcionSeguimiento", updated.getDescripcionSeguimiento());
            seguimientoMap.put("notasTecnicas", updated.getNotasTecnicas());
            seguimientoMap.put("fechaRegistro", updated.getFechaRegistro());
            seguimientoMap.put("estadoRegistro", updated.getEstadoRegistro());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Seguimiento actualizado exitosamente");
            result.put("seguimiento", seguimientoMap);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Error al actualizar el seguimiento: " + e.getMessage());

            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Elimina un seguimiento de trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Obtiene todos los seguimientos de una orden de trabajo específica")
    @GetMapping("/getByOrdenTrabajo/{idOrden}")
    public ResponseEntity<List<Map<String, Object>>> getByOrdenTrabajo(@PathVariable Integer idOrden) {
        try {
            List<TtSeguimientoTrabajo> seguimientos = service.findByOrdenTrabajo(idOrden);

            List<Map<String, Object>> resultado = seguimientos.stream().map(s -> {
                Map<String, Object> map = new HashMap<>();
                map.put("idSeguimientoTrabajo", s.getIdSeguimientoTrabajo());
                map.put("nombreSeguimiento", s.getNombreSeguimiento());
                map.put("descripcionSeguimiento", s.getDescripcionSeguimiento());
                map.put("notasTecnicas", s.getNotasTecnicas());
                map.put("fechaRegistro", s.getFechaRegistro());
                map.put("estadoRegistro", s.getEstadoRegistro());

                // Estado del seguimiento
                if (s.getEstadoSeguimiento() != null) {
                    map.put("estadoNombre", s.getEstadoSeguimiento().getNombreEstadoSeguimiento());
                    map.put("estadoDescripcion", s.getEstadoSeguimiento().getDescripcionEstadoSeguimiento());
                } else {
                    map.put("estadoNombre", null);
                    map.put("estadoDescripcion", null);
                }

                return map;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
