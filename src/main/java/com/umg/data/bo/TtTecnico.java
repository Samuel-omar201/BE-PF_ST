package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_tecnico")
@Getter
@Setter
public class TtTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTecnico;

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String carnetEmpleado;
    private Integer telefono;
    private Long dpi;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
