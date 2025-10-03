package com.umg.service;

import com.umg.data.bo.TtSeguimientoTrabajo;

import java.util.List;
import java.util.Optional;

public interface TtSeguimientoTrabajoService {

    List<TtSeguimientoTrabajo> findAll();
    Optional<TtSeguimientoTrabajo> findById(Integer id);
    TtSeguimientoTrabajo update(TtSeguimientoTrabajo ttSeguimientoTrabajo);
    TtSeguimientoTrabajo deleteById(Integer id);
}
