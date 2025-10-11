package com.umg.web.controller;

import com.umg.data.bo.TtFactura;
import com.umg.service.TtFacturaService;
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
@RequestMapping("/service/Autex_M1/ttFactura")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Manejo de facturas", protocols = "http")
public class TtFacturaController {

    @Autowired
    private TtFacturaService service;

    // ✅ NUEVO ENDPOINT - Con toda la información detallada
    @ApiOperation("Obtiene todas las facturas con información de orden de trabajo, cliente y vehículo")
    @GetMapping("/getAllDetailed")
    public ResponseEntity<List<Map<String, Object>>> getAllDetailed() {
        try {
            List<TtFactura> facturas = service.findAllWithDetails();

            List<Map<String, Object>> resultado = facturas.stream().map(f -> {
                Map<String, Object> map = new HashMap<>();

                // Datos de la factura
                map.put("idFactura", f.getIdFactura());
                map.put("noFactura", f.getNoFactura());
                map.put("fechaFactura", f.getFechaFactura());
                map.put("totalFactura", f.getTotalFactura());
                map.put("descripcionFactura", f.getDescripcionFactura());
                map.put("fechaRegistro", f.getFechaRegistro());
                map.put("estadoRegistro", f.getEstadoRegistro());

                // Datos de la orden de trabajo y relaciones
                if (f.getOrdenTrabajo() != null) {
                    map.put("idOrdenTrabajo", f.getOrdenTrabajo().getIdOrdenTrabajo());
                    map.put("descripcionOrden", f.getOrdenTrabajo().getDescripcionOrden());

                    // Datos del cliente
                    if (f.getOrdenTrabajo().getCliente() != null) {
                        String nombreCompleto = f.getOrdenTrabajo().getCliente().getPrimerNombre() + " " +
                                f.getOrdenTrabajo().getCliente().getPrimerApellido();
                        map.put("clienteNombre", nombreCompleto.trim());
                    } else {
                        map.put("clienteNombre", null);
                    }

                    // Datos del vehículo
                    if (f.getOrdenTrabajo().getVehiculo() != null) {
                        map.put("vehiculoDescripcion", f.getOrdenTrabajo().getVehiculo().getDescripcion());
                        map.put("vehiculoPlaca", f.getOrdenTrabajo().getVehiculo().getPlaca());
                        map.put("vehiculoMarca", f.getOrdenTrabajo().getVehiculo().getMarca());
                        map.put("vehiculoModelo", f.getOrdenTrabajo().getVehiculo().getModelo());
                    } else {
                        map.put("vehiculoDescripcion", null);
                        map.put("vehiculoPlaca", null);
                        map.put("vehiculoMarca", null);
                        map.put("vehiculoModelo", null);
                    }

                    // Estado de la orden
                    if (f.getOrdenTrabajo().getEstadoOrdenTrabajo() != null) {
                        map.put("estadoOrden", f.getOrdenTrabajo().getEstadoOrdenTrabajo().getDescripcionEstadoOrden());
                    } else {
                        map.put("estadoOrden", null);
                    }
                } else {
                    map.put("idOrdenTrabajo", null);
                    map.put("descripcionOrden", null);
                    map.put("clienteNombre", null);
                    map.put("vehiculoDescripcion", null);
                    map.put("vehiculoPlaca", null);
                    map.put("vehiculoMarca", null);
                    map.put("vehiculoModelo", null);
                    map.put("estadoOrden", null);
                }

                return map;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Obtiene todas las facturas")
    @GetMapping("/getAll")
    ResponseEntity<List<TtFactura>> getAll() {
        List<TtFactura> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Agrega una nueva factura")
    @PostMapping("/save")
    ResponseEntity<TtFactura> save(@RequestBody TtFactura entity) {
        TtFactura response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Actualiza una factura")
    @PutMapping("/update")
    ResponseEntity<TtFactura> update(@RequestBody TtFactura entity) {
        TtFactura response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina una factura")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        try {
            TtFactura deleted = this.service.deleteById(id);

            if (deleted != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Factura eliminada correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Factura no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al eliminar la factura");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}