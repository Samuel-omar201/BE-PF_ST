package com.umg.data.repository;

import com.umg.data.bo.TcEstadoOrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcEstadoOrdenTrabajoRepository extends JpaRepository<TcEstadoOrdenTrabajo, Integer> {

}
