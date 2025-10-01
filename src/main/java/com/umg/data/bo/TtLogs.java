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
    private Integer idLogs;

    private Integer ttUsuarioIdUsuario;
    private String accionLog;
    private String descripcionLog;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
