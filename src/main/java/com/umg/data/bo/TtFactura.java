package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_factura")
@Getter
@Setter
public class TtFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @Column(name = "fecha_factura")
    private LocalDate fechaFactura;

    @Column(name = "total_factura")
    private BigDecimal totalFactura;

    @Column(name = "descripcion_factura")
    private String descripcionFactura;

    // Campo Integer para el ID (compatibilidad)
    @Column(name = "tt_orden_trabajo_id_orden_trabajo", insertable = false, updatable = false)
    private Integer ttOrdenTrabajoIdOrdenTrabajo;

    // ✅ Relación con Orden de Trabajo - CORRECCIÓN AQUÍ
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "id_orden_trabajo")  // ✅ Cambiar a nombre de columna de BD
    private TtOrdenTrabajo ordenTrabajo;

    @Column(name = "no_factura")
    private String noFactura;

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