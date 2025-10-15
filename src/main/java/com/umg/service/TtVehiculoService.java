package com.umg.service;

import com.umg.data.bo.TtVehiculo;

import java.util.List;
import java.util.Optional;

public interface TtVehiculoService {

    List<TtVehiculo> findAll();
    Optional<TtVehiculo> findById(Integer id);
    TtVehiculo update(TtVehiculo ttVehiculo);

    TtVehiculo save(TtVehiculo vehiculo);
    TtVehiculo deleteById(Integer id);

    TtVehiculo deleteLogically(Integer id);

    List<TtVehiculo> findAllWithCliente();
}
