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
    private Integer idArchivosSeguimiento;

    private Integer ttSeguimientoTrabajoIdSeguimientoTrabajo;
    private byte[] archivo;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
