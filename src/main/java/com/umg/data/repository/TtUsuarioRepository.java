package com.umg.data.repository;

import com.umg.data.bo.TtUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TtUsuarioRepository extends JpaRepository<TtUsuario, Integer> {

    boolean existsByCorreoPrincipal(String correoPrincipal);
    boolean existsByNombreUsuario(String nombreUsuario);

    // Buscar usuario por correo con sus roles cargados
    @Query("SELECT u FROM TtUsuario u " +
            "LEFT JOIN FETCH u.roles r " +
            "LEFT JOIN FETCH r.rol " +
            "LEFT JOIN FETCH u.cliente " +
            "LEFT JOIN FETCH u.tecnico " +
            "WHERE u.correoPrincipal = :correo AND u.estadoRegistro = '1'")
    Optional<TtUsuario> findByCorreoPrincipalWithRoles(@Param("correo") String correo);
}