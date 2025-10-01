package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_usuario")
@Getter
@Setter
public class TtUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private Integer ttClienteIdCliente;
    private Integer ttTecnicoIdTecnico;
    private String nombreUsuario;
    private String correoPrincipal;
    private String correoSecundario;
    private String contraseña;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
