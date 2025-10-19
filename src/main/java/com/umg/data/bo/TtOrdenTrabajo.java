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
    @Column(name = "id_orden_trabajo")
    private Integer idOrdenTrabajo;

    // ✅ IMPORTANTE: Sin insertable=false, updatable=false para permitir actualizaciones por ID
    @Column(name = "tt_cliente_id_cliente")
    private Integer ttClienteIdCliente;

    @Column(name = "tt_vehiculo_id_vehiculo")
    private Integer ttVehiculoIdVehiculo;

    @Column(name = "tc_estado_orden_trabajo_id_estado_orden_trabajo")
    private Integer tcEstadoOrdenTrabajoIdEstadoOrdenTrabajo;

    // ✅ Relaciones con insertable=false, updatable=false en @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private TtCliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_vehiculo_id_vehiculo", referencedColumnName = "id_vehiculo", insertable = false, updatable = false)
    private TtVehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_estado_orden_trabajo_id_estado_orden_trabajo", referencedColumnName = "id_estado_orden_trabajo", insertable = false, updatable = false)
    private TcEstadoOrdenTrabajo estadoOrdenTrabajo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
    private String estadoRegistro;

    @Column(name = "descripcion_orden")
    private String descripcionOrden;

    @Column(name = "costo_final")
    private String costoFinal;

    @Column(name = "fecha_inicio_orden")
    private LocalDate fechaInicioOrden;

    @Column(name = "fecha_fin_orden")
    private LocalDate fechaFinOrden;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (estadoRegistro == null) {
            estadoRegistro = "1";
        }
    }
}