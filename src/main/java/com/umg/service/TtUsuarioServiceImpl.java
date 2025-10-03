package com.umg.service;

import com.umg.data.bo.TtUsuario;
import com.umg.data.repository.TtUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtUsuarioServiceImpl implements TtUsuarioService {

    private final TtUsuarioRepository repository;

    public TtUsuarioServiceImpl(TtUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtUsuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtUsuario> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtUsuario update(TtUsuario ttUsuario) {
        return repository.save(ttUsuario);
    }

    @Override
    public TtUsuario deleteById(Integer id) {
        Optional<TtUsuario> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
