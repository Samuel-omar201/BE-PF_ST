package com.umg.service;

import com.umg.data.bo.TtCliente;

import java.util.List;
import java.util.Optional;

public interface TtClienteService {

    List<TtCliente> findAll();
    Optional<TtCliente> findById(Integer id);
    TtCliente update(TtCliente ttCliente);
    TtCliente deleteById(Integer id);
}
