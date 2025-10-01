package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_orden_trabajo")
@Getter
@Setter
public class TtOrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdenTrabajo;

    private Integer ttClienteIdCliente;
    private Integer ttVehiculoIdVehiculo;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
    private String descripcionOrden;
    private String costoFinal;
    private Integer tcEstadoOrdenTrabajoIdEstadoOrdenTrabajo;
    private LocalDate fechaInicioOrden;
    private LocalDate fechaFinOrden;
}
