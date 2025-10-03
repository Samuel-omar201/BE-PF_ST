package com.umg.service;

import com.umg.data.bo.TtTecnico;
import com.umg.data.repository.TtTecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtTecnicoServiceImpl implements TtTecnicoService {

    private final TtTecnicoRepository repository;

    public TtTecnicoServiceImpl(TtTecnicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtTecnico> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtTecnico> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtTecnico update(TtTecnico ttTecnico) {
        return repository.save(ttTecnico);
    }

    @Override
    public TtTecnico deleteById(Integer id) {
        Optional<TtTecnico> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
