package com.umg.data.repository;

import com.umg.data.bo.TtUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TtUsuarioRepository extends JpaRepository<TtUsuario, Integer> {

    // Validar si el correo ya existe
    boolean existsByCorreoPrincipal(String correoPrincipal);

    // Validar si el nombre de usuario ya existe
    boolean existsByNombreUsuario(String nombreUsuario);


}
