package com.umg.service;

import com.umg.data.bo.TcServicios;
import com.umg.data.repository.TcServiciosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TcServiciosServiceImpl implements TcServiciosService {

    private final TcServiciosRepository repository;

    public TcServiciosServiceImpl(TcServiciosRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TcServicios> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TcServicios> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TcServicios update(TcServicios tcServicios) {
        return repository.save(tcServicios);
    }

    @Override
    public TcServicios deleteById(Integer id) {
        Optional<TcServicios> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
