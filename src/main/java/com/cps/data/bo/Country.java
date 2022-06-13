package com.cps.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Setter
@Getter
public class Country {

    @Id
    private Integer id;
    private Integer idRegion;
    private String name;
}
