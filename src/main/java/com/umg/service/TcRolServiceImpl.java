package com.umg.service;

import com.umg.data.bo.TcRol;
import com.umg.data.repository.TcRolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TcRolServiceImpl implements TcRolService {

    private final TcRolRepository repository;

    public TcRolServiceImpl(TcRolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TcRol> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TcRol> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TcRol update(TcRol tcRol) {
        return repository.save(tcRol);
    }

    @Override
    public TcRol deleteById(Integer id) {
        Optional<TcRol> entityToDelete = repository.findById(id);

        if (entityToDelete.isPresent()) {
            repository.deleteById(id);
            return entityToDelete.get();
        } else {
            return null;
        }
    }
}
