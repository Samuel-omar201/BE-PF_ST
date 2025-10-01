package com.umg.service;

import com.umg.data.bo.TcRol;

import java.util.List;
import java.util.Optional;

public interface TcRolService {

    List<TcRol> findAll();
    Optional<TcRol> findById(Integer id);
    TcRol update(TcRol tcRol);
    TcRol deleteById(Integer id);
}
