package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_vehiculo")
@Getter
@Setter
public class TtVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    // ✅ Campo para recibir/enviar el ID del cliente (compatibilidad con frontend)
    @Column(name = "tt_cliente_id_cliente", insertable = false, updatable = false)
    private Integer ttClienteIdCliente;

    // ✅ Relación con Cliente (solo para consultas)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", referencedColumnName = "idCliente")
    private TtCliente cliente;

    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String anioModelo;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro; // '0' = inactivo, '1' = activo

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