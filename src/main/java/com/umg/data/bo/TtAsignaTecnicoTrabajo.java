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
    private Integer idAsignaTecnicoTrabajo;

    private Integer ttTecnicoIdTecnico;
    private Integer ttOrdenTrabajoIdOrdenTrabajo;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
