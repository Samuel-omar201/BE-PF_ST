package com.umg.service;

import com.umg.data.bo.TtTecnico;

import java.util.List;
import java.util.Optional;

public interface TtTecnicoService {

    List<TtTecnico> findAll();
    Optional<TtTecnico> findById(Integer id);
    TtTecnico update(TtTecnico ttTecnico);
    TtTecnico deleteById(Integer id);
}
