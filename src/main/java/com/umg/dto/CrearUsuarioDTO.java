package com.umg.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CrearUsuarioDTO {

    // Datos del Usuario
    private String nombreUsuario;
    private String correoPrincipal;
    private String correoSecundario;
    private String contraseña;

    // Tipo de usuario: "cliente" o "tecnico"
    private String tipoUsuario;

    // Datos del Cliente (solo si tipoUsuario = "cliente")
    private String clientePrimerNombre;
    private String clienteSegundoNombre;
    private String clientePrimerApellido;
    private String clienteSegundoApellido;
    private LocalDate clienteFechaNacimiento;
    private Integer clienteTelefono;
    private String clienteDireccion;
    private Long clienteDpi;

    // Datos del Técnico (solo si tipoUsuario = "tecnico")
    private String tecnicoPrimerNombre;
    private String tecnicoSegundoNombre;
    private String tecnicoPrimerApellido;
    private String tecnicoSegundoApellido;
    private String tecnicoCarnetEmpleado;
    private Integer tecnicoTelefono;
    private Long tecnicoDpi;
}