package com.umg.service;

import com.umg.data.bo.TtFactura;
import com.umg.data.repository.TtFacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtFacturaServiceImpl implements TtFacturaService {

    private final TtFacturaRepository repository;

    public TtFacturaServiceImpl(TtFacturaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtFactura> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtFactura> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtFactura update(TtFactura ttFactura) {
        return repository.save(ttFactura);
    }

    @Override
    public TtFactura deleteById(Integer id) {
        Optional<TtFactura> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
