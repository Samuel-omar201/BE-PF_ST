package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_cliente")
@Getter
@Setter
public class TtCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private Integer telefono;
    private String direccion;
    private Long dpi;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro; // ENUM('0','1') -> String
}
