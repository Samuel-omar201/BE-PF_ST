package com.umg.service;

import com.umg.data.bo.TtOrdenTrabajo;
import com.umg.data.repository.TtOrdenTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtOrdenTrabajoServiceImpl implements TtOrdenTrabajoService {

    private final TtOrdenTrabajoRepository repository;

    public TtOrdenTrabajoServiceImpl(TtOrdenTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtOrdenTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtOrdenTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtOrdenTrabajo update(TtOrdenTrabajo entity) {
        return repository.save(entity);
    }

    @Override
    public TtOrdenTrabajo deleteById(Integer id) {
        Optional<TtOrdenTrabajo> entityToDelete = repository.findById(id);

        if (entityToDelete.isPresent()) {
            repository.deleteById(id);
            return entityToDelete.get();
        } else {
            return null;
        }
    }
}
