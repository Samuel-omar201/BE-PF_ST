package com.umg.data.repository;

import com.umg.data.bo.TtFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtFacturaRepository extends JpaRepository<TtFactura, Integer> {

    // ✅ Query optimizada que carga la orden de trabajo y sus relaciones
    @Query("SELECT f FROM TtFactura f " +
            "LEFT JOIN FETCH f.ordenTrabajo ot " +
            "LEFT JOIN FETCH ot.cliente " +
            "LEFT JOIN FETCH ot.vehiculo " +
            "LEFT JOIN FETCH ot.estadoOrdenTrabajo " +
            "ORDER BY f.fechaFactura DESC")
    List<TtFactura> findAllWithDetails();
}