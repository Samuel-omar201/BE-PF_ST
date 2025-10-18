package com.umg.service;

import com.umg.data.bo.TcEstadoSeguimientoTrabajo;
import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.bo.TtSeguimientoTrabajo;
import com.umg.data.repository.TcEstadoSeguimientoTrabajoRepository;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import com.umg.data.repository.TtSeguimientoTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TtSeguimientoTrabajoServiceImpl implements TtSeguimientoTrabajoService {

    private final TtSeguimientoTrabajoRepository repository;

    @Autowired
    private TcEstadoSeguimientoTrabajoRepository estadoRepository;

    @Autowired
    private TtOrdenTrabajoRepository ordenTrabajoRepository;

    public TtSeguimientoTrabajoServiceImpl(TtSeguimientoTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtSeguimientoTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtSeguimientoTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtSeguimientoTrabajo update(TtSeguimientoTrabajo ttSeguimientoTrabajo) {
        return repository.save(ttSeguimientoTrabajo);
    }

    @Override
    public TtSeguimientoTrabajo deleteById(Integer id) {
        Optional<TtSeguimientoTrabajo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TtSeguimientoTrabajo> findByOrdenTrabajo(Integer idOrden) {
        return repository.findByOrdenTrabajoWithEstado(idOrden);
    }

    @Override
    @Transactional
    public TtSeguimientoTrabajo save(TtSeguimientoTrabajo seguimiento) {
        // Establecer valores por defecto
        if (seguimiento.getFechaRegistro() == null) {
            seguimiento.setFechaRegistro(LocalDateTime.now());
        }
        if (seguimiento.getEstadoRegistro() == null) {
            seguimiento.setEstadoRegistro("1");
        }

        // ✅ CRÍTICO: Establecer las relaciones JPA correctamente

        // Establecer relación con Orden de Trabajo
        if (seguimiento.getTtOrdenTrabajoIdOrdenTrabajo() != null && seguimiento.getOrdenTrabajo() == null) {
            TtOrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(seguimiento.getTtOrdenTrabajoIdOrdenTrabajo())
                    .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada: " + seguimiento.getTtOrdenTrabajoIdOrdenTrabajo()));
            seguimiento.setOrdenTrabajo(ordenTrabajo);
        }

        // Establecer relación con Estado de Seguimiento
        if (seguimiento.getTcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo() != null && seguimiento.getEstadoSeguimiento() == null) {
            TcEstadoSeguimientoTrabajo estado = estadoRepository.findById(seguimiento.getTcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo())
                    .orElseThrow(() -> new RuntimeException("Estado de seguimiento no encontrado: " + seguimiento.getTcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo()));
            seguimiento.setEstadoSeguimiento(estado);
        }

        return repository.save(seguimiento);
    }


}
