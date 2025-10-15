package com.umg.data.repository;

import com.umg.data.bo.TtAsignaRolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TtAsignaRolUsuarioRepository extends JpaRepository<TtAsignaRolUsuario, Integer> {
}