package com.umg.service;

import com.umg.data.bo.TtFactura;

import java.util.List;
import java.util.Optional;

public interface TtFacturaService {

    List<TtFactura> findAll();
    Optional<TtFactura> findById(Integer id);
    TtFactura update(TtFactura ttFactura);
    TtFactura deleteById(Integer id);
}
