package com.umg.service;

import com.umg.data.bo.TtAsignaRolUsuario;
import com.umg.data.bo.TtUsuario;
import com.umg.data.repository.TtUsuarioRepository;
import com.umg.dto.*;
import com.umg.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private TtUsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Jerarquía de roles (mayor número = más permisos)
    private static final Map<String, Integer> ROL_HIERARCHY = new HashMap<>();
    static {
        ROL_HIERARCHY.put("Cliente", 1);
        ROL_HIERARCHY.put("Tecnico", 2);
        ROL_HIERARCHY.put("Recepcionista", 3);
        ROL_HIERARCHY.put("Administrador", 4);
    }

    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();

        try {
            // Buscar usuario por correo con roles cargados
            Optional<TtUsuario> usuarioOpt = usuarioRepository.findByCorreoPrincipalWithRoles(
                    request.getCorreoPrincipal()
            );

            if (!usuarioOpt.isPresent()) {
                response.setSuccess(false);
                response.setMessage("Correo o contraseña incorrectos");
                return response;
            }

            TtUsuario usuario = usuarioOpt.get();

            // Validar contraseña (sin encriptación por ahora)
            if (!usuario.getContraseña().equals(request.getContraseña())) {
                response.setSuccess(false);
                response.setMessage("Correo o contraseña incorrectos");
                return response;
            }

            // Obtener roles del usuario
            List<RolDTO> roles = usuario.getRoles().stream()
                    .filter(asignacion -> "1".equals(asignacion.getEstadoRegistro()))
                    .map(asignacion -> {
                        RolDTO rolDTO = new RolDTO();
                        rolDTO.setIdRol(asignacion.getRol().getIdRol());
                        rolDTO.setNombreRol(asignacion.getRol().getNombreRol());
                        rolDTO.setDescripcionRol(asignacion.getRol().getDescripcionRol());
                        return rolDTO;
                    })
                    .collect(Collectors.toList());

            if (roles.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("El usuario no tiene roles asignados");
                return response;
            }

            // Determinar el rol principal (el de mayor jerarquía)
            String rolPrincipal = determinarRolPrincipal(roles);

            // Construir información del usuario
            UsuarioInfoDTO usuarioInfo = new UsuarioInfoDTO();
            usuarioInfo.setIdUsuario(usuario.getIdUsuario());
            usuarioInfo.setNombreUsuario(usuario.getNombreUsuario());
            usuarioInfo.setCorreoPrincipal(usuario.getCorreoPrincipal());
            usuarioInfo.setRoles(roles);
            usuarioInfo.setRolPrincipal(rolPrincipal);

            // Determinar tipo de usuario y nombre completo
            if (usuario.getCliente() != null) {
                usuarioInfo.setTipoUsuario("cliente");
                String nombreCompleto = usuario.getCliente().getPrimerNombre() + " " +
                        usuario.getCliente().getPrimerApellido();
                usuarioInfo.setNombreCompleto(nombreCompleto.trim());
            } else if (usuario.getTecnico() != null) {
                usuarioInfo.setTipoUsuario("tecnico");
                String nombreCompleto = usuario.getTecnico().getPrimerNombre() + " " +
                        usuario.getTecnico().getPrimerApellido();
                usuarioInfo.setNombreCompleto(nombreCompleto.trim());
            } else {
                usuarioInfo.setTipoUsuario("sistema");
                usuarioInfo.setNombreCompleto(usuario.getNombreUsuario());
            }

            // Generar token JWT
            String token = jwtUtil.generateToken(
                    usuario.getIdUsuario(),
                    usuario.getCorreoPrincipal(),
                    rolPrincipal
            );

            // Preparar respuesta exitosa
            response.setSuccess(true);
            response.setMessage("Login exitoso");
            response.setToken(token);
            response.setUsuario(usuarioInfo);

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage("Error en el servidor: " + e.getMessage());
            return response;
        }
    }

    /**
     * Determina el rol con mayor jerarquía
     */
    private String determinarRolPrincipal(List<RolDTO> roles) {
        return roles.stream()
                .max(Comparator.comparingInt(rol ->
                        ROL_HIERARCHY.getOrDefault(rol.getNombreRol(), 0)))
                .map(RolDTO::getNombreRol)
                .orElse("Cliente");
    }

    /**
     * Valida un token JWT
     */
    public boolean validateToken(String token) {
        try {
            String correo = jwtUtil.extractUsername(token);
            return !jwtUtil.isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}