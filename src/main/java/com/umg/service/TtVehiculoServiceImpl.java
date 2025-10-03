package com.umg.service;

import com.umg.data.bo.TtVehiculo;
import com.umg.data.repository.TtVehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtVehiculoServiceImpl implements TtVehiculoService {

    private final TtVehiculoRepository repository;

    public TtVehiculoServiceImpl(TtVehiculoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtVehiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtVehiculo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtVehiculo update(TtVehiculo ttVehiculo) {
        return repository.save(ttVehiculo);
    }

    @Override
    public TtVehiculo deleteById(Integer id) {
        Optional<TtVehiculo> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
