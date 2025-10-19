package com.umg.web.controller;

import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.bo.TcEstadoOrdenTrabajo;
import com.umg.data.bo.TtCliente;
import com.umg.data.bo.TtVehiculo;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import com.umg.data.repository.TcEstadoOrdenTrabajoRepository;
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

    @Autowired
    private TcEstadoOrdenTrabajoRepository estadoOrdenRepository;

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
        TtOrdenTrabajo response = this.service.save(orden);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza orden de trabajo")
    @PutMapping("/update")
    ResponseEntity<Map<String, Object>> update(@RequestBody TtOrdenTrabajo orden) {
        try {
            TtOrdenTrabajo updated = this.service.update(orden);

            // ✅ Construir respuesta sin relaciones lazy para evitar error de serialización
            Map<String, Object> ordenMap = new HashMap<>();
            ordenMap.put("idOrdenTrabajo", updated.getIdOrdenTrabajo());
            ordenMap.put("descripcionOrden", updated.getDescripcionOrden());
            ordenMap.put("costoFinal", updated.getCostoFinal());
            ordenMap.put("fechaInicioOrden", updated.getFechaInicioOrden());
            ordenMap.put("fechaFinOrden", updated.getFechaFinOrden());
            ordenMap.put("fechaRegistro", updated.getFechaRegistro());
            ordenMap.put("estadoRegistro", updated.getEstadoRegistro());
            ordenMap.put("ttClienteIdCliente", updated.getTtClienteIdCliente());
            ordenMap.put("ttVehiculoIdVehiculo", updated.getTtVehiculoIdVehiculo());
            ordenMap.put("tcEstadoOrdenTrabajoIdEstadoOrdenTrabajo", updated.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Orden de trabajo actualizada exitosamente");
            result.put("ordenTrabajo", ordenMap);

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Error al actualizar la orden de trabajo: " + e.getMessage());

            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        try {
            // ✅ Usar el método existente que carga las relaciones
            List<TtOrdenTrabajo> reparaciones = service.findAllWithDetails();

            List<Map<String, Object>> resultado = reparaciones.stream().map(r -> {
                Map<String, Object> map = new HashMap<>();

                // Datos básicos de la orden
                map.put("idOrdenTrabajo", r.getIdOrdenTrabajo());
                map.put("descripcionOrden", r.getDescripcionOrden());
                map.put("costoFinal", r.getCostoFinal());
                map.put("fechaRegistro", r.getFechaRegistro());
                map.put("estadoRegistro", r.getEstadoRegistro());
                map.put("fechaInicioOrden", r.getFechaInicioOrden());
                map.put("fechaFinOrden", r.getFechaFinOrden());

                // ✅ IDs necesarios para actualización (NUEVOS)
                map.put("ttClienteIdCliente", r.getTtClienteIdCliente());
                map.put("ttVehiculoIdVehiculo", r.getTtVehiculoIdVehiculo());
                map.put("tcEstadoOrdenTrabajoIdEstadoOrdenTrabajo", r.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo());

                // ✅ Estado de la orden (usar getEstadoOrdenTrabajo que ya existe)
                try {
                    if (r.getEstadoOrdenTrabajo() != null) {
                        map.put("estadoOrden", r.getEstadoOrdenTrabajo().getNombreEstadoOrden());
                    } else {
                        map.put("estadoOrden", null);
                    }
                } catch (Exception e) {
                    // Fallback: buscar por ID si falla el lazy loading
                    if (r.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo() != null) {
                        try {
                            TcEstadoOrdenTrabajo estado = estadoOrdenRepository
                                    .findById(r.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo())
                                    .orElse(null);

                            if (estado != null) {
                                map.put("estadoOrden", estado.getNombreEstadoOrden());
                            } else {
                                map.put("estadoOrden", null);
                            }
                        } catch (Exception ex) {
                            map.put("estadoOrden", null);
                        }
                    } else {
                        map.put("estadoOrden", null);
                    }
                }

                // ✅ Construir nombre completo del cliente (mantener lógica existente)
                try {
                    if (r.getCliente() != null) {
                        TtCliente cliente = r.getCliente();
                        String nombreCompleto = String.format("%s %s",
                                cliente.getPrimerNombre() != null ? cliente.getPrimerNombre() : "",
                                cliente.getPrimerApellido() != null ? cliente.getPrimerApellido() : ""
                        ).trim();

                        map.put("clienteNombre", nombreCompleto);
                    } else {
                        map.put("clienteNombre", null);
                    }
                } catch (Exception e) {
                    map.put("clienteNombre", null);
                }

                // ✅ Acceder a descripción del vehículo (mantener lógica existente)
                try {
                    if (r.getVehiculo() != null) {
                        TtVehiculo vehiculo = r.getVehiculo();
                        map.put("vehiculoDescripcion", vehiculo.getDescripcion());
                    } else {
                        map.put("vehiculoDescripcion", null);
                    }
                } catch (Exception e) {
                    map.put("vehiculoDescripcion", null);
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