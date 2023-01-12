package com.honda.hdm.datacollect.model.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DcStateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;
    private String name;
    private List<DcCityDto> cities;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DcCityDto> getCities() {
        return cities;
    }

    public void setCities(List<DcCityDto> cities) {
        this.cities = cities;
    }

}
