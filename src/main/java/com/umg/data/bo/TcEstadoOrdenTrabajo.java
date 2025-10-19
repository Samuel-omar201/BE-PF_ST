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
    @Column(name = "id_estado_orden_trabajo")  // ✅ Especificar nombre de columna
    private Integer idEstadoOrdenTrabajo;

    @Column(name = "nombre_estado_orden")
    private String nombreEstadoOrden;

    @Column(name = "descripcion_estado_orden")
    private String descripcionEstadoOrden;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
    private String estadoRegistro;
}