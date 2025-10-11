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

    // ✅ Relación con Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", referencedColumnName = "idCliente")
    private TtCliente cliente;

    // ✅ Relación con Vehículo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_vehiculo_id_vehiculo", referencedColumnName = "idVehiculo")
    private TtVehiculo vehiculo;

    // ✅ Relación con Estado de Orden
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_estado_orden_trabajo_id_estado_orden_trabajo", referencedColumnName = "idEstadoOrdenTrabajo")
    private TcEstadoOrdenTrabajo estadoOrdenTrabajo;

    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
    private String descripcionOrden;
    private String costoFinal;
    private LocalDate fechaInicioOrden;
    private LocalDate fechaFinOrden;
}