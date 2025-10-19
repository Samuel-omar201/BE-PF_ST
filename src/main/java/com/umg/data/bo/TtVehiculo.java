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
    @Column(name = "id_vehiculo")  // ✅ Especificar nombre de columna
    private Integer idVehiculo;

    @Column(name = "tt_cliente_id_cliente", insertable = false, updatable = false)
    private Integer ttClienteIdCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", referencedColumnName = "id_cliente")  // ✅ Usar nombre de columna de BD
    private TtCliente cliente;

    private String placa;
    private String marca;
    private String modelo;
    private String color;

    @Column(name = "anio_modelo")
    private String anioModelo;

    private String descripcion;

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