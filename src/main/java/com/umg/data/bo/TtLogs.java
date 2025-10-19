package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_logs")
@Getter
@Setter
public class TtLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logs")
    private Integer idLogs;

    @Column(name = "tt_usuario_id_usuario", insertable = false, updatable = false)
    private Integer ttUsuarioIdUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_usuario_id_usuario", referencedColumnName = "id_usuario")
    private TtUsuario usuario;

    @Column(name = "accion_log")
    private String accionLog;

    @Column(name = "descripcion_log")
    private String descripcionLog;

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