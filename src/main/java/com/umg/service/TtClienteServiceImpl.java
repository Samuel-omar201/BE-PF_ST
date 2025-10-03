package com.umg.service;

import com.umg.data.bo.TtCliente;
import com.umg.data.repository.TtClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtClienteServiceImpl implements TtClienteService {

    private final TtClienteRepository repository;

    public TtClienteServiceImpl(TtClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtCliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtCliente> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtCliente update(TtCliente ttCliente) {
        return repository.save(ttCliente);
    }

    @Override
    public TtCliente deleteById(Integer id) {
        Optional<TtCliente> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
