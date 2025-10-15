package com.umg.service;

import com.umg.data.bo.TtUsuario;
import com.umg.dto.CrearUsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface TtUsuarioService {

    List<TtUsuario> findAll();
    Optional<TtUsuario> findById(Integer id);
    TtUsuario update(TtUsuario ttUsuario);
    TtUsuario deleteById(Integer id);
    TtUsuario crearUsuario(CrearUsuarioDTO dto);

}
