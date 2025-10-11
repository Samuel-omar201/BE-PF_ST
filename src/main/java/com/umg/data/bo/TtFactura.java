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
    private Integer idFactura;

    private LocalDate fechaFactura;
    private BigDecimal totalFactura;
    private String descripcionFactura;

    // ✅ Relación con Orden de Trabajo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "idOrdenTrabajo")
    private TtOrdenTrabajo ordenTrabajo;

    private String noFactura;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}