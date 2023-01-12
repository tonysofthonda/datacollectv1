package com.honda.hdm.datacollect.web.auth;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.service.domain.impl.DcViewActionService;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPermissionHandler {

    private static final Logger LOGGER = LogManager.getLogger(UserPermissionHandler.class);

    @Autowired
    private DcViewActionService viewActionService;

    private List<DcViewAction> allowedActions;

    private boolean permissionsLoaded = false;

    public static class PermissionsNotLoadedException extends Exception {

        public PermissionsNotLoadedException() {
        }

        public PermissionsNotLoadedException(String message) {
            super(message);
        }

        public PermissionsNotLoadedException(Throwable cause) {
            super(cause);
        }
    }

    public boolean isPermissionsLoaded() {
        return permissionsLoaded;
    }

    public void loadUserPermissions(Authentication authentication) {
        List<String> userAssginedLdapGroups = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            userAssginedLdapGroups.add(authority.getAuthority());
        });
        allowedActions = viewActionService.findAllByLdapGroups(userAssginedLdapGroups);
        permissionsLoaded = true;
    }

    public List<DcViewAction> getUserPermissions() throws PermissionsNotLoadedException {
        if (!isPermissionsLoaded()) {
            throw new PermissionsNotLoadedException("Error trying to validate permissions while user permissions are not loaded");
        }
        return allowedActions;
    }
}
