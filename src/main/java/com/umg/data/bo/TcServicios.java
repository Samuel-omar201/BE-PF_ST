package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tc_servicios")
@Getter
@Setter
public class TcServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicios")  // ✅ Agregar esto
    private Integer idServicios;

    @Column(name = "nombre_servicio")
    private String nombreServicio;

    @Column(name = "descripcion_servicio")
    private String descripcionServicio;

    @Column(name = "costo_base_servicio")
    private BigDecimal costoBaseServicio;

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