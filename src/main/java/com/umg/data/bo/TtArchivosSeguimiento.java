package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_archivos_seguimiento")
@Getter
@Setter
public class TtArchivosSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivos_seguimiento")
    private Integer idArchivosSeguimiento;

    @Column(name = "tt_seguimiento_trabajo_id_seguimiento_trabajo", insertable = false, updatable = false)
    private Integer ttSeguimientoTrabajoIdSeguimientoTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_seguimiento_trabajo_id_seguimiento_trabajo", referencedColumnName = "id_seguimiento_trabajo")
    private TtSeguimientoTrabajo seguimientoTrabajo;

    private byte[] archivo;

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