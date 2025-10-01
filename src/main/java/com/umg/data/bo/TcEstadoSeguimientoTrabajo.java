package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tc_estado_seguimiento_trabajo")
@Getter
@Setter
public class TcEstadoSeguimientoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoSeguimientoTrabajo;

    private String nombreEstadoSeguimiento;
    private String descripcionEstadoSeguimiento;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
