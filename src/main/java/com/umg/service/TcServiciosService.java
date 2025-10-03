package com.umg.service;

import com.umg.data.bo.TcServicios;

import java.util.List;
import java.util.Optional;

public interface TcServiciosService {

    List<TcServicios> findAll();
    Optional<TcServicios> findById(Integer id);
    TcServicios update(TcServicios tcServicios);
    TcServicios deleteById(Integer id);
}
