package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_asigna_rol_usuario")
@Getter
@Setter
public class TtAsignaRolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsignaRolUsuario;

    private Integer ttUsuarioIdUsuario;
    private Integer tcRolIdRol;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
