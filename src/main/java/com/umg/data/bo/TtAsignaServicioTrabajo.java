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
    @Column(name = "id_asigna_servicio_trabajo")
    private Integer idAsignaServicioTrabajo;

    @Column(name = "tt_orden_trabajo_id_orden_trabajo", insertable = false, updatable = false)
    private Integer ttOrdenTrabajoIdOrdenTrabajo;

    // ✅ CORRECCIÓN
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "id_orden_trabajo")
    private TtOrdenTrabajo ordenTrabajo;

    @Column(name = "tc_servicios_id_servicios", insertable = false, updatable = false)
    private Integer tcServiciosIdServicios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_servicios_id_servicios", referencedColumnName = "id_servicios")
    private TcServicios servicio;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
    private String estadoRegistro;

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