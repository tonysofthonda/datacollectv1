package com.honda.hdm.datacollect.model.entity.dto;

import java.io.Serializable;

public class DcWorkshopDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
