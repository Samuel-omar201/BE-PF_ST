package com.umg.service;

import com.umg.data.bo.TtSeguimientoTrabajo;
import com.umg.data.repository.TtSeguimientoTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtSeguimientoTrabajoServiceImpl implements TtSeguimientoTrabajoService {

    private final TtSeguimientoTrabajoRepository repository;

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
}
