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
    @Column(name = "id_estado_seguimiento_trabajo")
    private Integer idEstadoSeguimientoTrabajo;

    @Column(name = "nombre_estado_seguimiento")
    private String nombreEstadoSeguimiento;

    @Column(name = "descripcion_estado_seguimiento")
    private String descripcionEstadoSeguimiento;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
    private String estadoRegistro;
}