package com.umg.service;

import com.umg.data.bo.TcEstadoSeguimientoTrabajo;
import com.umg.data.repository.TcEstadoSeguimientoTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TcEstadoSeguimientoTrabajoServiceImpl implements TcEstadoSeguimientoTrabajoService {

    private final TcEstadoSeguimientoTrabajoRepository repository;

    public TcEstadoSeguimientoTrabajoServiceImpl(TcEstadoSeguimientoTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TcEstadoSeguimientoTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TcEstadoSeguimientoTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TcEstadoSeguimientoTrabajo update(TcEstadoSeguimientoTrabajo entity) {
        return repository.save(entity);
    }

    @Override
    public TcEstadoSeguimientoTrabajo deleteById(Integer id) {
        Optional<TcEstadoSeguimientoTrabajo> entityToDelete = repository.findById(id);

        if (entityToDelete.isPresent()) {
            repository.deleteById(id);
            return entityToDelete.get();
        } else {
            return null;
        }
    }
}
