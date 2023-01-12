/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.honda.hdm.datacollect.web.auth.UserPermissionHandler;

/**
 *
 * @author VJC80439
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	UserPermissionHandler permissionHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        SessionFlashMapManager flashMapManager= new SessionFlashMapManager();
        
        FlashMap flashMap = new FlashMap();
        
        flashMap.put("success", "You have started session correctly");
        
        flashMapManager.saveOutputFlashMap(flashMap, request, response);
        
        if(authentication != null){
            logger.info("The user: '"+authentication.getName()+"' has started sesion correctly");
        }
        
        //load user permissions by session
        permissionHandler.loadUserPermissions(authentication);
        
        super.onAuthenticationSuccess(request, response, authentication); 
    }
    
}
