package com.umg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String correoPrincipal;
    private String contraseña;
}