package com.honda.hdm.datacollect.web.dto;

import java.util.List;

public class RoleDto {

	private Long id;
	private String name;
	private String description;
	private List<LdapGroupDto> ldapGroupList;
	private List<ViewActionDto> permissionList;

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

	public List<LdapGroupDto> getLdapGroupList() {
		return ldapGroupList;
	}

	public void setLdapGroupList(List<LdapGroupDto> ldapGroupList) {
		this.ldapGroupList = ldapGroupList;
	}

	public List<ViewActionDto> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<ViewActionDto> permissionList) {
		this.permissionList = permissionList;
	}
}
