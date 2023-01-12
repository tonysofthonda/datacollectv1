package com.honda.hdm.datacollect.web.navigation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.entity.DcMenuCategory;
import com.honda.hdm.datacollect.model.entity.DcView;
import com.honda.hdm.datacollect.service.domain.impl.DcMenuCategoryService;
import com.honda.hdm.datacollect.web.auth.UserPermissionHandler;
import com.honda.hdm.datacollect.web.auth.UserPermissionHandler.PermissionsNotLoadedException;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NavigationHelper {
	
	@Autowired
	private UserPermissionHandler permissionHandler;
	
	@Autowired
	private DcMenuCategoryService menuCategoriesService;
		
	public List<DcMenuCategory> getMenuCategories() throws PermissionsNotLoadedException{
		List<String> allowedViews = permissionHandler.getUserPermissions().stream()
													.filter(viewAction -> viewAction.getShortName().equalsIgnoreCase("view"))
													.map(viewAction -> viewAction.getView().getName()).collect(Collectors.toList());
		
		List<DcMenuCategory> menuCategories = menuCategoriesService.findAll();			
		menuCategories.forEach(menuCategory -> {
			List<DcView> views = menuCategory.getViews().stream().filter(view -> allowedViews.contains(view.getName())).collect(Collectors.toList());
			menuCategory.setViews(views);
		});
		
		return menuCategories.stream().filter(menu-> menu.getViews().size() > 0).collect(Collectors.toList());			
	}
}
