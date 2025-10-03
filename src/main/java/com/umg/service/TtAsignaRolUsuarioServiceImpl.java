package com.umg.service;

import com.umg.data.bo.TtAsignaRolUsuario;
import com.umg.data.repository.TtAsignaRolUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtAsignaRolUsuarioServiceImpl implements TtAsignaRolUsuarioService {

    private final TtAsignaRolUsuarioRepository repository;

    public TtAsignaRolUsuarioServiceImpl(TtAsignaRolUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtAsignaRolUsuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtAsignaRolUsuario> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtAsignaRolUsuario update(TtAsignaRolUsuario ttAsignaRolUsuario) {
        return repository.save(ttAsignaRolUsuario);
    }

    @Override
    public TtAsignaRolUsuario deleteById(Integer id) {
        Optional<TtAsignaRolUsuario> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
