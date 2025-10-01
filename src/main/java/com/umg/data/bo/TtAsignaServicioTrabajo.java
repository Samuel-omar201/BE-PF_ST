package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_asigna_servicio_trabajo")
@Getter
@Setter
public class TtAsignaServicioTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignaServicioTrabajo;

    private Integer ttOrdenTrabajoIdOrdenTrabajo;
    private Integer tcServiciosIdServicios;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
