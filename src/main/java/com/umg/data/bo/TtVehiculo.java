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

    private Integer ttClienteIdCliente;
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private String anioModelo;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
