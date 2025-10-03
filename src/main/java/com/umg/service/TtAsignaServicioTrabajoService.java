package com.umg.service;

import com.umg.data.bo.TtAsignaServicioTrabajo;

import java.util.List;
import java.util.Optional;

public interface TtAsignaServicioTrabajoService {

    List<TtAsignaServicioTrabajo> findAll();
    Optional<TtAsignaServicioTrabajo> findById(Integer id);
    TtAsignaServicioTrabajo update(TtAsignaServicioTrabajo ttAsignaServicioTrabajo);
    TtAsignaServicioTrabajo deleteById(Integer id);
}
