package com.umg.service;

import com.umg.data.bo.TcEstadoOrdenTrabajo;
import com.umg.data.repository.TcEstadoOrdenTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TcEstadoOrdenTrabajoServiceImpl implements TcEstadoOrdenTrabajoService {

    private final TcEstadoOrdenTrabajoRepository repository;

    public TcEstadoOrdenTrabajoServiceImpl(TcEstadoOrdenTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TcEstadoOrdenTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TcEstadoOrdenTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TcEstadoOrdenTrabajo update(TcEstadoOrdenTrabajo entity) {
        return repository.save(entity);
    }

    @Override
    public TcEstadoOrdenTrabajo deleteById(Integer id) {
        Optional<TcEstadoOrdenTrabajo> entityToDelete = repository.findById(id);

        if (entityToDelete.isPresent()) {
            repository.deleteById(id);
            return entityToDelete.get();
        } else {
            return null;
        }
    }
}
