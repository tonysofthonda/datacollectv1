package com.honda.hdm.datacollect.web.auth;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.web.auth.UserPermissionHandler.PermissionsNotLoadedException;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator{
	
	private static final Logger LOGGER = LogManager.getLogger(UserPermissionEvaluator.class);
	
	@Autowired
	UserPermissionHandler userPermissionHandler;
	
	public boolean hasPermission(Object targetObject, Object permission) {
		return hasPermission(null, targetObject, permission);
	}	
	
	@Override
	public boolean hasPermission(Authentication auth, Object targetObject, Object permission){
		if ((targetObject == null) || !(targetObject instanceof String)  || !(permission instanceof String)){
            return false;
        }
		LOGGER.info("checking permissions for view:" + ((String)targetObject) + ", permission:" +((String)permission));
		try {
			String targetView = ((String)targetObject).trim();
			String targetPermission = ((String)permission).trim();
			List<DcViewAction> userPermissions = userPermissionHandler.getUserPermissions();
			return userPermissions.stream().anyMatch(
					viewAction -> 
						viewAction.getView().getName().trim().equalsIgnoreCase(targetView) && viewAction.getShortName().trim().equalsIgnoreCase(targetPermission) 
			);
		} catch (PermissionsNotLoadedException e) {
			LOGGER.error("Unable to get permissions");
			return false;
		}
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		throw new UnsupportedOperationException();
	}
}
