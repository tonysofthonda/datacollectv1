package com.honda.hdm.datacollect.web.dto;

public class LdapGroupDto {
	
	private Long id;
	private String name;
	private String ldapId;
	
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
}
