package com.umg.data.repository;

import com.umg.data.bo.TtOrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TtOrdenTrabajoRepository extends JpaRepository<TtOrdenTrabajo, Integer> {

    // ✅ Query optimizada que carga todas las relaciones en una sola consulta
    @Query("SELECT ot FROM TtOrdenTrabajo ot " +
            "LEFT JOIN FETCH ot.cliente " +
            "LEFT JOIN FETCH ot.vehiculo " +
            "LEFT JOIN FETCH ot.estadoOrdenTrabajo")
    List<TtOrdenTrabajo> findAllWithDetails();

}
