package com.umg.service;

import com.umg.data.bo.TcEstadoOrdenTrabajo;

import java.util.List;
import java.util.Optional;

public interface TcEstadoOrdenTrabajoService {

    List<TcEstadoOrdenTrabajo> findAll();
    Optional<TcEstadoOrdenTrabajo> findById(Integer id);
    TcEstadoOrdenTrabajo update(TcEstadoOrdenTrabajo tcEstadoOrdenTrabajo);
    TcEstadoOrdenTrabajo deleteById(Integer id);
}
