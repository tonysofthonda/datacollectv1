package com.honda.hdm.datacollect.model.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DcCityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;
    private String name;
    private DcStateDto state;

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

    public DcStateDto getState() {
        return state;
    }

    public void setState(DcStateDto state) {
        this.state = state;
    }

}
