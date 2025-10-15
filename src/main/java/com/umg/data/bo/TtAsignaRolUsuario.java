// ========== TtAsignaRolUsuario.java ==========
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

    // Campo Integer para compatibilidad
    @Column(name = "tt_usuario_id_usuario", insertable = false, updatable = false)
    private Integer ttUsuarioIdUsuario;

    // Relación con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_usuario_id_usuario", referencedColumnName = "idUsuario")
    private TtUsuario usuario;

    // Campo Integer para compatibilidad
    @Column(name = "tc_rol_id_rol", insertable = false, updatable = false)
    private Integer tcRolIdRol;

    // Relación con Rol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_rol_id_rol", referencedColumnName = "idRol")
    private TcRol rol;

    private LocalDateTime fechaRegistro;
    private String estadoRegistro;

    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (estadoRegistro == null) {
            estadoRegistro = "1";
        }
    }
}