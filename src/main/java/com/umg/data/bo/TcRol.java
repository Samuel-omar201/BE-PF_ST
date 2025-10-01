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
    private Integer idRol;

    private String nombreRol;
    private String descripcionRol;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
