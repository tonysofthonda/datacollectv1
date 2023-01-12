package com.honda.hdm.datacollect.model.entity.dto.auth;

import com.honda.hdm.datacollect.model.entity.dto.DcPositionDto;
import com.honda.hdm.datacollect.validation.RoleValid;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@RoleValid
public class DcRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String name;

    private String description;

    private List<DcPositionDto> positions;

    private List<DcLdapGroupDto> ldapGroups;

    private List<DcViewActionDto> permissions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DcPositionDto> getPositions() {
        return positions;
    }

    public void setPositions(List<DcPositionDto> positions) {
        this.positions = positions;
    }

    public List<DcLdapGroupDto> getLdapGroups() {
        return ldapGroups;
    }

    public void setLdapGroups(List<DcLdapGroupDto> ldapGroups) {
        this.ldapGroups = ldapGroups;
    }

    public List<DcViewActionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<DcViewActionDto> permissions) {
        this.permissions = permissions;
    }
}
