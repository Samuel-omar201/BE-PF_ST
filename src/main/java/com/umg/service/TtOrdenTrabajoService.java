package com.umg.service;

import com.umg.data.bo.TtOrdenTrabajo;

import java.util.List;
import java.util.Optional;

public interface TtOrdenTrabajoService {

    List<TtOrdenTrabajo> findAll();

    // ✅ NUEVO: Método para obtener órdenes con relaciones cargadas
    List<TtOrdenTrabajo> findAllWithDetails();

    Optional<TtOrdenTrabajo> findById(Integer id);

    TtOrdenTrabajo update(TtOrdenTrabajo ttOrdenTrabajo);

    TtOrdenTrabajo deleteById(Integer id);
}