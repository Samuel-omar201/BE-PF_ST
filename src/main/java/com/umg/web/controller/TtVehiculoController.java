package com.umg.web.controller;

import com.umg.data.bo.TtVehiculo;
import com.umg.service.TtVehiculoService;
import com.umg.data.repository.TtVehiculoRepository;
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
@RequestMapping("/service/Autex_M1/ttVehiculo")
@CrossOrigin(origins = "*")
@Api(value = "Manejo de vehículos", protocols = "http")
public class TtVehiculoController {

    @Autowired
    private TtVehiculoRepository repository;

    @Autowired
    private TtVehiculoService service;

    @ApiOperation("Obtiene todos los vehículos")
    @GetMapping("/getAll")
    ResponseEntity<List<TtVehiculo>> getAll() {
        List<TtVehiculo> response = this.service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ✅ NUEVO: Obtener todos con información del cliente
    @ApiOperation("Obtiene todos los vehículos con información del cliente")
    @GetMapping("/getAllDetailed")
    public ResponseEntity<List<Map<String, Object>>> getAllDetailed() {
        try {
            List<TtVehiculo> vehiculos = service.findAllWithCliente();

            List<Map<String, Object>> resultado = vehiculos.stream().map(v -> {
                Map<String, Object> map = new HashMap<>();
                map.put("idVehiculo", v.getIdVehiculo());
                map.put("placa", v.getPlaca());
                map.put("marca", v.getMarca());
                map.put("modelo", v.getModelo());
                map.put("color", v.getColor());
                map.put("anioModelo", v.getAnioModelo());
                map.put("descripcion", v.getDescripcion());
                map.put("fechaRegistro", v.getFechaRegistro());
                map.put("estadoRegistro", v.getEstadoRegistro());

                // Información del cliente
                if (v.getCliente() != null) {
                    String nombreCompleto = v.getCliente().getPrimerNombre() + " " +
                            v.getCliente().getPrimerApellido();
                    map.put("idCliente", v.getCliente().getIdCliente());
                    map.put("clienteNombre", nombreCompleto.trim());
                } else {
                    map.put("idCliente", null);
                    map.put("clienteNombre", "Sin asignar");
                }

                return map;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(resultado, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Obtiene un vehículo por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<TtVehiculo> getById(@PathVariable Integer id) {
        Optional<TtVehiculo> vehiculo = this.service.findById(id);
        return vehiculo.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation("Agrega un nuevo vehículo")
    @PostMapping("/save")
    ResponseEntity<Map<String, Object>> save(@RequestBody TtVehiculo entity) {
        try {
            TtVehiculo response = this.service.save(entity);

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "Vehículo creado exitosamente");
            result.put("vehiculo", response);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("success", false);
            result.put("message", "Error al crear el vehículo: " + e.getMessage());

            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Actualiza un vehículo")
    @PutMapping("/update")
    ResponseEntity<TtVehiculo> update(@RequestBody TtVehiculo entity) {
        TtVehiculo response = this.service.update(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Elimina un vehículo")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ NUEVO: Eliminación lógica (cambiar estado a '0')
    @ApiOperation("Desactiva un vehículo (eliminación lógica)")
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<Map<String, Object>> desactivar(@PathVariable Integer id) {
        try {
            TtVehiculo vehiculo = service.deleteLogically(id);

            if (vehiculo != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Vehículo desactivado correctamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Vehículo no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al desactivar el vehículo");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
