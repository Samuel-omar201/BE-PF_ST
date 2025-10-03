package com.umg.service;

import com.umg.data.bo.TtArchivosSeguimiento;

import java.util.List;
import java.util.Optional;

public interface TtArchivosSeguimientoService {

    List<TtArchivosSeguimiento> findAll();
    Optional<TtArchivosSeguimiento> findById(Integer id);
    TtArchivosSeguimiento update(TtArchivosSeguimiento ttArchivosSeguimiento);
    TtArchivosSeguimiento deleteById(Integer id);
}
