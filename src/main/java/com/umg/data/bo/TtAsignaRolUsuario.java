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
    @Column(name = "id_asigna_rol_usuario")
    private Integer idAsignaRolUsuario;

    @Column(name = "tt_usuario_id_usuario", insertable = false, updatable = false)
    private Integer ttUsuarioIdUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_usuario_id_usuario", referencedColumnName = "id_usuario")
    private TtUsuario usuario;

    @Column(name = "tc_rol_id_rol", insertable = false, updatable = false)
    private Integer tcRolIdRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tc_rol_id_rol", referencedColumnName = "id_rol")
    private TcRol rol;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_registro")
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