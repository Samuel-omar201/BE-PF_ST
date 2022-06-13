package com.cps.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client {

    @Id
    private Integer id;
    private String identificationNumber;
    private String code;
    private Integer level;
    private Float discount;

}
