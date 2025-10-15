package com.umg.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDTO {
    private boolean success;
    private String message;
    private String token;
    private UsuarioInfoDTO usuario;
}