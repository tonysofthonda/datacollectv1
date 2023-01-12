package com.honda.hdm.datacollect.web.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuDTO {	
	private Map<String, List<MenuCategoryItem>> categories;
	
	public void setCategories(Map<String, List<MenuCategoryItem>> categories) {
		this.categories = categories;
	}
	
	public Map<String, List<MenuCategoryItem>> getCategories() {
		//clone map to avoid external modifications
		if(categories == null ) {
			return categories;
		}
		return categories.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public boolean containsCategory(String categoryName) {
		if(categories == null) {
			return false;
		}
		return categories.containsKey(categoryName);
	}
	
	public void addCategory(String categoryName, List<MenuCategoryItem> items) {
		if(categories == null) {
			categories = new HashMap<>();
		}
		categories.put(categoryName, items);
	}
	
	public void addCategoryItem(String categoryName, MenuCategoryItem item) {
		if(categories.containsKey(categoryName)) {
			if(categories.get(categoryName) == null) {
				categories.put(categoryName, new ArrayList<MenuCategoryItem>());
			}
			categories.get(categoryName).add(item);
		}
	}
	
	public void setCategoryItems(String categoryName, List<MenuCategoryItem> items) {
		if(categories.containsKey(categoryName)) {
			if(categories.get(categoryName) == null) {
				categories.put(categoryName, new ArrayList<MenuCategoryItem>());
			}
			categories.put(categoryName, items);
		}
	}
	
	
	public void removeCategory(String categoryName) {
		if(categories.containsKey(categoryName)) {
			categories.remove(categoryName);
		}		
	}
	
	public static class MenuCategoryItem{
		private String name;
		private String route;
		
		public MenuCategoryItem(String name, String route) {
			this.name = name;
			this.route = route;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}		
		
		public String getRoute() {
			return route;
		}
		
		public void setRoute(String route) {
			this.route = route;
		}		
	}
}
