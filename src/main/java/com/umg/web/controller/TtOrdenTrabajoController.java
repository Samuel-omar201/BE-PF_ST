package com.umg.web.controller;

import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import com.umg.service.TtOrdenTrabajoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service/Autex_M1/ttOrdenTrabajo")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de órdenes de trabajo", protocols = "http")
public class TtOrdenTrabajoController {

    @Autowired
    private TtOrdenTrabajoRepository repository;

    @Autowired
    private TtOrdenTrabajoService service;

    @GetMapping("/test")
    public String test() {
        return "Backend funcionando correctamente";
    }

    @ApiOperation("Obtiene todas las órdenes de trabajo")
    @GetMapping("/getAll")
    ResponseEntity<List<TtOrdenTrabajo>> getAll() {
        List<TtOrdenTrabajo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Obtiene una orden de trabajo por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<TtOrdenTrabajo> getById(@PathVariable Integer id) {
        Optional<TtOrdenTrabajo> orden = this.service.findById(id);
        return orden.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation("Agrega una nueva orden de trabajo")
    @PostMapping("/save")
    ResponseEntity<TtOrdenTrabajo> save(@RequestBody TtOrdenTrabajo orden) {
        TtOrdenTrabajo response = this.service.update(orden);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza orden de trabajo")
    @PutMapping("/update")
    ResponseEntity<TtOrdenTrabajo> update(@RequestBody TtOrdenTrabajo orden) {
        TtOrdenTrabajo response = this.service.update(orden);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina orden de trabajo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("Obtiene todas las reparaciones con información de cliente, vehículo y estado")
    @GetMapping("/getAllDetailed")
    public ResponseEntity<List<Map<String, Object>>> getAllDetailed() {
        // ✅ Usar el nuevo método del servicio que carga las relaciones
        List<TtOrdenTrabajo> reparaciones = service.findAllWithDetails();

        List<Map<String, Object>> resultado = reparaciones.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("idOrdenTrabajo", r.getIdOrdenTrabajo());
            map.put("descripcionOrden", r.getDescripcionOrden());
            map.put("costoFinal", r.getCostoFinal());
            map.put("fechaRegistro", r.getFechaRegistro());
            map.put("estadoRegistro", r.getEstadoRegistro());
            map.put("fechaInicioOrden", r.getFechaInicioOrden());
            map.put("fechaFinOrden", r.getFechaFinOrden());

            // ✅ Acceder a la entidad relacionada
            if (r.getEstadoOrdenTrabajo() != null) {
                map.put("estadoOrden", r.getEstadoOrdenTrabajo().getDescripcionEstadoOrden());
            } else {
                map.put("estadoOrden", null);
            }

            // ✅ Construir nombre completo del cliente
            if (r.getCliente() != null) {
                String nombreCompleto = r.getCliente().getPrimerNombre() + " " +
                        r.getCliente().getPrimerApellido();
                map.put("clienteNombre", nombreCompleto.trim());
            } else {
                map.put("clienteNombre", null);
            }

            // ✅ Acceder a descripción del vehículo
            if (r.getVehiculo() != null) {
                map.put("vehiculoDescripcion", r.getVehiculo().getDescripcion());
            } else {
                map.put("vehiculoDescripcion", null);
            }

            return map;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
