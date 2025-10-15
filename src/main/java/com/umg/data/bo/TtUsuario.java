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

    // ✅ Relación opcional con Cliente (puede ser NULL si es técnico)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_cliente_id_cliente", referencedColumnName = "idCliente", nullable = true)
    private TtCliente cliente;

    // ✅ Relación opcional con Técnico (puede ser NULL si es cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_tecnico_id_tecnico", referencedColumnName = "idTecnico", nullable = true)
    private TtTecnico tecnico;

    private String nombreUsuario;
    private String correoPrincipal;
    private String correoSecundario;
    private String contraseña;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro; // ENUM('0','1')

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