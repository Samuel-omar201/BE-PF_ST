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
    @Column(name = "id_seguimiento_trabajo")
    private Integer idSeguimientoTrabajo;

    @Column(name = "tt_orden_trabajo_id_orden_trabajo", insertable = false, updatable = false)
    private Integer ttOrdenTrabajoIdOrdenTrabajo;

    // ✅ CORRECCIÓN: referencedColumnName debe ser "id_orden_trabajo"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "id_orden_trabajo")
    private TtOrdenTrabajo ordenTrabajo;

    @Column(name = "tc_estado_seguimiento_trabajo_id_estado_seguimiento_trabajo", insertable = false, updatable = false)
    private Integer tcEstadoSeguimientoTrabajoIdEstadoSeguimientoTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_estado_seguimiento_trabajo_id_estado_seguimiento_trabajo", referencedColumnName = "id_estado_seguimiento_trabajo")
    private TcEstadoSeguimientoTrabajo estadoSeguimiento;

    @Column(name = "notas_tecnicas")
    private String notasTecnicas;

    @Column(name = "nombre_seguimiento")
    private String nombreSeguimiento;

    @Column(name = "descripcion_seguimiento")
    private String descripcionSeguimiento;

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