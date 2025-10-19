package com.umg.data.repository;

import com.umg.data.bo.TtVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtVehiculoRepository extends JpaRepository<TtVehiculo, Integer> {

    // ✅ Obtener todos los vehículos con información del cliente
    @Query("SELECT v FROM TtVehiculo v " +
            "LEFT JOIN FETCH v.cliente " +
            "ORDER BY v.idVehiculo DESC")
    List<TtVehiculo> findAllWithCliente();

    // ✅ Obtener solo vehículos activos
    @Query("SELECT v FROM TtVehiculo v " +
            "LEFT JOIN FETCH v.cliente " +
            "WHERE v.estadoRegistro = '1' " +
            "ORDER BY v.idVehiculo DESC")
    List<TtVehiculo> findAllActive();

}
