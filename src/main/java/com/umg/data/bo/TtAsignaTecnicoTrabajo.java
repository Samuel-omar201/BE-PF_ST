package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tt_asigna_tecnico_trabajo")
@Getter
@Setter
public class TtAsignaTecnicoTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asigna_tecnico_trabajo")
    private Integer idAsignaTecnicoTrabajo;

    @Column(name = "tt_tecnico_id_tecnico", insertable = false, updatable = false)
    private Integer ttTecnicoIdTecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_tecnico_id_tecnico", referencedColumnName = "id_tecnico")
    private TtTecnico tecnico;

    @Column(name = "tt_orden_trabajo_id_orden_trabajo", insertable = false, updatable = false)
    private Integer ttOrdenTrabajoIdOrdenTrabajo;

    // ✅ CORRECCIÓN
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tt_orden_trabajo_id_orden_trabajo", referencedColumnName = "id_orden_trabajo")
    private TtOrdenTrabajo ordenTrabajo;

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