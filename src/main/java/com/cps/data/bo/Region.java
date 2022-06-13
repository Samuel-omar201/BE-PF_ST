package com.cps.data.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region")
@Setter
@Getter
public class Region {

    @Id
    private Integer id;
    private String name;
    private Float rate;

}
