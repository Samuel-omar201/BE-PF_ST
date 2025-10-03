package com.umg.service;

import com.umg.data.bo.TtAsignaRolUsuario;

import java.util.List;
import java.util.Optional;

public interface TtAsignaRolUsuarioService {

    List<TtAsignaRolUsuario> findAll();
    Optional<TtAsignaRolUsuario> findById(Integer id);
    TtAsignaRolUsuario update(TtAsignaRolUsuario ttAsignaRolUsuario);
    TtAsignaRolUsuario deleteById(Integer id);
}
