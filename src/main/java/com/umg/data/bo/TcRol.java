package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tc_rol")
@Getter
@Setter
public class TcRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "descripcion_rol")
    private String descripcionRol;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
    private String estadoRegistro;
}