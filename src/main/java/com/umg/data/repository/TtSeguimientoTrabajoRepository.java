package com.umg.data.repository;

import com.umg.data.bo.TtSeguimientoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TtSeguimientoTrabajoRepository extends JpaRepository<TtSeguimientoTrabajo, Integer> {

    // Obtener seguimientos de una orden específica con estado cargado
    @Query("SELECT s FROM TtSeguimientoTrabajo s " +
            "LEFT JOIN FETCH s.estadoSeguimiento " +
            "WHERE s.ttOrdenTrabajoIdOrdenTrabajo = :idOrden " +
            "AND s.estadoRegistro = '1' " +
            "ORDER BY s.fechaRegistro ASC")
    List<TtSeguimientoTrabajo> findByOrdenTrabajoWithEstado(@Param("idOrden") Integer idOrden);
}