package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tc_servicios")
@Getter
@Setter
public class TcServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicios;

    private String nombreServicio;
    private String descripcionServicio;
    private BigDecimal costoBaseServicio;
    private LocalDateTime fechaRegistro;
    private String estadoRegistro;
}
