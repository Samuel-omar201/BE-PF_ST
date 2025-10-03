package com.umg.service;

import com.umg.data.bo.TtAsignaTecnicoTrabajo;

import java.util.List;
import java.util.Optional;

public interface TtAsignaTecnicoTrabajoService {

    List<TtAsignaTecnicoTrabajo> findAll();
    Optional<TtAsignaTecnicoTrabajo> findById(Integer id);
    TtAsignaTecnicoTrabajo update(TtAsignaTecnicoTrabajo ttAsignaTecnicoTrabajo);
    TtAsignaTecnicoTrabajo deleteById(Integer id);
}
