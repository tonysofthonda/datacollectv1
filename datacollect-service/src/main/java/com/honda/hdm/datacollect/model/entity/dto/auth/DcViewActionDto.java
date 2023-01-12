package com.honda.hdm.datacollect.model.entity.dto.auth;

import java.io.Serializable;
import java.util.List;

public class DcViewActionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String shortName;

    private String friendlyName;

    private DcViewDto view;

    private List<DcRoleDto> assignedRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public DcViewDto getView() {
        return view;
    }

    public void setView(DcViewDto view) {
        this.view = view;
    }

    public List<DcRoleDto> getAssignedRoles() {
        return assignedRoles;
    }

    public void setAssignedRoles(List<DcRoleDto> assignedRoles) {
        this.assignedRoles = assignedRoles;
    }
}
