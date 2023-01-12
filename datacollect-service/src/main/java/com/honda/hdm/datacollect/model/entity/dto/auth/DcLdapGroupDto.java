package com.honda.hdm.datacollect.model.entity.dto.auth;

import com.honda.hdm.datacollect.validation.LdapGroupValid;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@LdapGroupValid
public class DcLdapGroupDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String ldapId;

    private List<DcRoleDto> roles;

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

    public String getLdapId() {
        return ldapId;
    }

    public void setLdapId(String ldapId) {
        this.ldapId = ldapId;
    }

    public List<DcRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<DcRoleDto> roles) {
        this.roles = roles;
    }
}
