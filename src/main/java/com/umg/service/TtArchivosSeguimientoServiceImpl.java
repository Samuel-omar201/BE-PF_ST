package com.umg.service;

import com.umg.data.bo.TtArchivosSeguimiento;
import com.umg.data.repository.TtArchivosSeguimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtArchivosSeguimientoServiceImpl implements TtArchivosSeguimientoService {

    private final TtArchivosSeguimientoRepository repository;

    public TtArchivosSeguimientoServiceImpl(TtArchivosSeguimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtArchivosSeguimiento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtArchivosSeguimiento> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtArchivosSeguimiento update(TtArchivosSeguimiento ttArchivosSeguimiento) {
        return repository.save(ttArchivosSeguimiento);
    }

    @Override
    public TtArchivosSeguimiento deleteById(Integer id) {
        Optional<TtArchivosSeguimiento> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            return entity.get();
        }
        return null;
    }
}
