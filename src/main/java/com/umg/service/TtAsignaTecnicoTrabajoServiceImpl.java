package com.umg.service;

import com.umg.data.bo.TtAsignaTecnicoTrabajo;
import com.umg.data.repository.TtAsignaTecnicoTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtAsignaTecnicoTrabajoServiceImpl implements TtAsignaTecnicoTrabajoService {

    private final TtAsignaTecnicoTrabajoRepository repository;

    public TtAsignaTecnicoTrabajoServiceImpl(TtAsignaTecnicoTrabajoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtAsignaTecnicoTrabajo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtAsignaTecnicoTrabajo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtAsignaTecnicoTrabajo update(TtAsignaTecnicoTrabajo ttAsignaTecnicoTrabajo) {
        return repository.save(ttAsignaTecnicoTrabajo);
    }

    @Override
    public TtAsignaTecnicoTrabajo deleteById(Integer id) {
        Optional<TtAsignaTecnicoTrabajo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
