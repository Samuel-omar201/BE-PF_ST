package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_seguimiento_trabajo")
@Getter
@Setter
public class TtSeguimientoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSeguimientoTrabajo;

    private Integer ttOrdenTrabajoIdOrdenTrabajo;
    private String notasTecnicas;
    private String nombreSeguimiento;
    private String descripcionSeguimiento;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
    private Integer tcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo;
}
