package com.umg.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Setter
@Getter
public class Car {

    @Id
    private Integer id;
    private Integer idEstado;
    private String  marca;
    private Integer modelo;
    private Float precio;

}
