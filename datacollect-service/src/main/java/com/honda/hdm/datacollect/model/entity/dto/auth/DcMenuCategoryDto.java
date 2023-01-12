package com.honda.hdm.datacollect.model.entity.dto.auth;

import java.io.Serializable;
import java.util.List;

public class DcMenuCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer order;

    private List<DcViewDto> views;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<DcViewDto> getViews() {
        return views;
    }

    public void setViews(List<DcViewDto> views) {
        this.views = views;
    }
}
