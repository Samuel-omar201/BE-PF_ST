package com.umg.data.repository;

import com.umg.data.bo.TcRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TcRolRepository extends JpaRepository<TcRol, Integer> {
    Optional<TcRol> findByNombreRol(String nombreRol);
}