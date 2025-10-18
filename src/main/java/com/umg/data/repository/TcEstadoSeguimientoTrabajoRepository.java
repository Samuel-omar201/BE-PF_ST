package com.umg.data.repository;

import com.umg.data.bo.TcEstadoSeguimientoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcEstadoSeguimientoTrabajoRepository extends JpaRepository<TcEstadoSeguimientoTrabajo, Integer> {

}
