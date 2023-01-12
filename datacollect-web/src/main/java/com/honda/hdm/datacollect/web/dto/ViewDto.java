package com.honda.hdm.datacollect.web.dto;

import java.util.List;

public class ViewDto {
	
	private Long id;
	private String name;
	private String friendlyName;
	private String route;
	private int order;
	private List<ViewActionDto> actions;
	
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public List<ViewActionDto> getActions() {
		return actions;
	}
	public void setActions(List<ViewActionDto> actions) {
		this.actions = actions;
	}
}
