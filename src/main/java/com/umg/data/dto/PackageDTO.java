package com.umg.data.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PackageDTO {

    private String code;
    private Integer countryId;
    private Float Weight;
    private Float rate;
    private Float height;
    private Float length;
    private Float width;
    private Float discount;

}
