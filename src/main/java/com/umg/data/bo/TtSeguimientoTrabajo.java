// ========== TtSeguimientoTrabajo.java ==========
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

    // Campo Integer para compatibilidad
    @Column(name = "tt_orden_trabajo_id_orden_trabajo", insertable = false, updatable = false)
    private Integer ttOrdenTrabajoIdOrdenTrabajo;

    // Relación con Orden de Trabajo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "idOrdenTrabajo")
    private TtOrdenTrabajo ordenTrabajo;

    // Campo Integer para compatibilidad
    @Column(name = "tc_estado_seguimiento_trabajo_id_estado_seguimiento_trabajo", insertable = false, updatable = false)
    private Integer tcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo;

    // Relación con Estado de Seguimiento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_estado_seguimiento_trabajo_id_estado_seguimiento_trabajo", referencedColumnName = "idEstadoSeguimientoTrabajo")
    private TcEstadoSeguimientoTrabajo estadoSeguimiento;

    private String notasTecnicas;
    private String nombreSeguimiento;
    private String descripcionSeguimiento;
    private LocalDateTime fechaRegistro;
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