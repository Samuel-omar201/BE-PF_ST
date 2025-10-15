package com.umg.web.controller;

import com.umg.dto.LoginRequestDTO;
import com.umg.dto.LoginResponseDTO;
import com.umg.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/Autex_M1/auth")
@CrossOrigin(origins = "*")
@Api(value = "Autenticación", protocols = "http")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("Iniciar sesión")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            LoginResponseDTO response = authService.login(request);

            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LoginResponseDTO errorResponse = new LoginResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error en el servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Validar token")
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            // Remover "Bearer " del token si existe
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = authService.validateToken(token);
            return new ResponseEntity<>(isValid, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }
}