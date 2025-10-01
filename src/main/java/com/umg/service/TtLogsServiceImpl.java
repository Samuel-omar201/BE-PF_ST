package com.umg.service;

import com.umg.data.bo.TtLogs;
import com.umg.data.repository.TtLogsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TtLogsServiceImpl implements TtLogsService {

    private final TtLogsRepository repository;

    public TtLogsServiceImpl(TtLogsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TtLogs> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TtLogs> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TtLogs update(TtLogs ttLogs) {
        return repository.save(ttLogs);
    }

    @Override
    public TtLogs deleteById(Integer id) {
        Optional<TtLogs> entityToDelete = repository.findById(id);

        if (entityToDelete.isPresent()) {
            repository.deleteById(id);
            return entityToDelete.get();
        } else {
            return null;
        }
    }
}
