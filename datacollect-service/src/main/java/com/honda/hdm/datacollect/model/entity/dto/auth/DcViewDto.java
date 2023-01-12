package com.honda.hdm.datacollect.model.entity.dto.auth;

import java.io.Serializable;
import java.util.List;

public class DcViewDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String friendlyName;

    private String route;

    private Integer order;

    private DcMenuCategoryDto menuCategory;

    private List<DcViewActionDto> viewActions;

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

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public DcMenuCategoryDto getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(DcMenuCategoryDto menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<DcViewActionDto> getViewActions() {
        return viewActions;
    }

    public void setViewActions(List<DcViewActionDto> viewActions) {
        this.viewActions = viewActions;
    }
}
