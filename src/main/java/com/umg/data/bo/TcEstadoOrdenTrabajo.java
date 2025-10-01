package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tc_estado_orden_trabajo")
@Getter
@Setter
public class TcEstadoOrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoOrdenTrabajo;

    private String nombreEstadoOrden;
    private String descripcionEstadoOrden;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
