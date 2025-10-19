package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tt_usuario")
@Getter
@Setter
public class TtUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    // Campos Integer para compatibilidad
    @Column(name = "tt_cliente_id_cliente", insertable = false, updatable = false)
    private Integer ttClienteIdCliente;

    @Column(name = "tt_tecnico_id_tecnico", insertable = false, updatable = false)
    private Integer ttTecnicoIdTecnico;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", nullable = true)
    private TtCliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_tecnico_id_tecnico", nullable = true)
    private TtTecnico tecnico;

    // Relación con roles (un usuario puede tener múltiples roles)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<TtAsignaRolUsuario> roles;

    private String nombreUsuario;
    private String correoPrincipal;
    private String correoSecundario;
    private String contraseña;
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