package com.umg.service;

import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.bo.TtCliente;
import com.umg.data.bo.TtVehiculo;
import com.umg.data.bo.TcEstadoOrdenTrabajo;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import com.umg.data.repository.TtClienteRepository;
import com.umg.data.repository.TtVehiculoRepository;
import com.umg.data.repository.TcEstadoOrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TtOrdenTrabajoServiceImpl  implements TtOrdenTrabajoService {

    @Autowired
    private TtOrdenTrabajoRepository repository;

    @Autowired
    private TtClienteRepository clienteRepository;

    @Autowired
    private TtVehiculoRepository vehiculoRepository;

    @Autowired
    private TcEstadoOrdenTrabajoRepository estadoOrdenRepository;

    /**
     * Obtiene todas las órdenes sin cargar relaciones (para listados simples)
     */
    @Override
    public List<TtOrdenTrabajo> findAll() {
        return repository.findAll();
    }

    /**
     * Obtiene todas las órdenes CON relaciones cargadas (para vista detallada)
     * Este método ya existe en tu código original
     */
    @Override
    @Transactional(readOnly = true)
    public List<TtOrdenTrabajo> findAllWithDetails() {
        List<TtOrdenTrabajo> ordenes = repository.findAll();

        // Forzar la carga de las relaciones lazy
        ordenes.forEach(orden -> {
            if (orden.getCliente() != null) {
                orden.getCliente().getPrimerNombre(); // Inicializa el proxy
            }
            if (orden.getVehiculo() != null) {
                orden.getVehiculo().getDescripcion(); // Inicializa el proxy
            }
            if (orden.getEstadoOrdenTrabajo() != null) {
                orden.getEstadoOrdenTrabajo().getNombreEstadoOrden(); // Inicializa el proxy
            }
        });

        return ordenes;
    }

    @Override
    public Optional<TtOrdenTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public TtOrdenTrabajo save(TtOrdenTrabajo orden) {
        if (orden.getFechaRegistro() == null) {
            orden.setFechaRegistro(LocalDateTime.now());
        }
        if (orden.getEstadoRegistro() == null) {
            orden.setEstadoRegistro("1");
        }

        // Establecer relaciones JPA
        establecerRelaciones(orden);

        return repository.save(orden);
    }

    @Override
    @Transactional
    public TtOrdenTrabajo update(TtOrdenTrabajo ordenActualizada) {
        // Verificar que la orden existe
        TtOrdenTrabajo ordenExistente = repository.findById(ordenActualizada.getIdOrdenTrabajo())
                .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada: " + ordenActualizada.getIdOrdenTrabajo()));

        // ✅ Preservar campos que NO deben cambiar
        // Mantener fechaRegistro original (no se debe modificar)
        // Mantener estadoRegistro si no se envió en la actualización

        // Actualizar solo los campos editables
        ordenExistente.setDescripcionOrden(ordenActualizada.getDescripcionOrden());
        ordenExistente.setCostoFinal(ordenActualizada.getCostoFinal());
        ordenExistente.setFechaInicioOrden(ordenActualizada.getFechaInicioOrden());
        ordenExistente.setFechaFinOrden(ordenActualizada.getFechaFinOrden());

        // ✅ Actualizar los IDs directamente (esto es lo que permite la actualización)
        if (ordenActualizada.getTtClienteIdCliente() != null) {
            ordenExistente.setTtClienteIdCliente(ordenActualizada.getTtClienteIdCliente());
        }
        if (ordenActualizada.getTtVehiculoIdVehiculo() != null) {
            ordenExistente.setTtVehiculoIdVehiculo(ordenActualizada.getTtVehiculoIdVehiculo());
        }
        if (ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo() != null) {
            ordenExistente.setTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo(ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo());
        }

        // Solo actualizar relaciones si los IDs cambiaron
        if (ordenActualizada.getTtClienteIdCliente() != null) {
            if (ordenExistente.getTtClienteIdCliente() == null ||
                    !ordenExistente.getTtClienteIdCliente().equals(ordenActualizada.getTtClienteIdCliente())) {

                TtCliente cliente = clienteRepository.findById(ordenActualizada.getTtClienteIdCliente())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + ordenActualizada.getTtClienteIdCliente()));
                ordenExistente.setCliente(cliente);
            }
        }

        if (ordenActualizada.getTtVehiculoIdVehiculo() != null) {
            if (ordenExistente.getTtVehiculoIdVehiculo() == null ||
                    !ordenExistente.getTtVehiculoIdVehiculo().equals(ordenActualizada.getTtVehiculoIdVehiculo())) {

                TtVehiculo vehiculo = vehiculoRepository.findById(ordenActualizada.getTtVehiculoIdVehiculo())
                        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado: " + ordenActualizada.getTtVehiculoIdVehiculo()));
                ordenExistente.setVehiculo(vehiculo);
            }
        }

        if (ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo() != null) {
            if (ordenExistente.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo() == null ||
                    !ordenExistente.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo().equals(ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo())) {

                TcEstadoOrdenTrabajo estado = estadoOrdenRepository.findById(ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo())
                        .orElseThrow(() -> new RuntimeException("Estado de orden no encontrado: " + ordenActualizada.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo()));
                ordenExistente.setEstadoOrdenTrabajo(estado);
            }
        }

        return repository.save(ordenExistente);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        // Eliminación lógica
        Optional<TtOrdenTrabajo> orden = repository.findById(id);
        if (orden.isPresent()) {
            TtOrdenTrabajo ordenTrabajo = orden.get();
            ordenTrabajo.setEstadoRegistro("0");
            repository.save(ordenTrabajo);
        }
    }

    /**
     * Establece las relaciones JPA basándose en los IDs
     * Solo establece la relación si el ID está presente y la relación aún no está seteada
     */
    private void establecerRelaciones(TtOrdenTrabajo orden) {
        // Establecer relación con Cliente
        if (orden.getTtClienteIdCliente() != null) {
            TtCliente cliente = clienteRepository.findById(orden.getTtClienteIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + orden.getTtClienteIdCliente()));
            orden.setCliente(cliente);
        }

        // Establecer relación con Vehículo
        if (orden.getTtVehiculoIdVehiculo() != null) {
            TtVehiculo vehiculo = vehiculoRepository.findById(orden.getTtVehiculoIdVehiculo())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado: " + orden.getTtVehiculoIdVehiculo()));
            orden.setVehiculo(vehiculo);
        }

        // Establecer relación con Estado
        if (orden.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo() != null) {
            TcEstadoOrdenTrabajo estado = estadoOrdenRepository.findById(orden.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo())
                    .orElseThrow(() -> new RuntimeException("Estado de orden no encontrado: " + orden.getTcEstadoOrdenTrabajoIdEstadoOrdenTrabajo()));
            orden.setEstadoOrdenTrabajo(estado);
        }
    }
}