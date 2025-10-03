package com.umg.service;

import com.umg.data.bo.TtAsignaServicioTrabajo;
import com.umg.data.repository.TtAsignaServicioTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtAsignaServicioTrabajoServiceImpl implements TtAsignaServicioTrabajoService {

    private final TtAsignaServicioTrabajoRepository repository;

    public TtAsignaServicioTrabajoServiceImpl(TtAsignaServicioTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtAsignaServicioTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtAsignaServicioTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtAsignaServicioTrabajo update(TtAsignaServicioTrabajo ttAsignaServicioTrabajo) {
        return repository.save(ttAsignaServicioTrabajo);
    }

    @Override
    public TtAsignaServicioTrabajo deleteById(Integer id) {
        Optional<TtAsignaServicioTrabajo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
