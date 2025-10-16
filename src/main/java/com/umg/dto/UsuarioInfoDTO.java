package com.umg.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioInfoDTO {
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoPrincipal;
    private String tipoUsuario; // "cliente" o "tecnico"
    private String nombreCompleto;
    private List<RolDTO> roles;
    private String rolPrincipal; // El rol con más permisos
    private Integer idCliente;
    private Integer idTecnico;
}