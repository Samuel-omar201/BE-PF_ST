package com.umg.service;

import com.umg.data.bo.TcEstadoSeguimientoTrabajo;

import java.util.List;
import java.util.Optional;

public interface TcEstadoSeguimientoTrabajoService {

    List<TcEstadoSeguimientoTrabajo> findAll();
    Optional<TcEstadoSeguimientoTrabajo> findById(Integer id);
    TcEstadoSeguimientoTrabajo update(TcEstadoSeguimientoTrabajo tcEstadoSeguimientoTrabajo);
    TcEstadoSeguimientoTrabajo deleteById(Integer id);
}
