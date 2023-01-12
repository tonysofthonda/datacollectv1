/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.web.controller.main;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.honda.hdm.datacollect.service.domain.impl.auth.DcRoleService;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Controller
public class WelcomeController {
    
    private static final Logger LOGGER = LogManager.getLogger(WelcomeController.class);
    
    @Autowired
    DcRoleService dcRoleService;
    
    @GetMapping(path={"/","/welcome","/home", "/dashboard"})
    public String home(Model model, HttpServletRequest request) {
        return "dashboard/dashboard-main";
    }
    
    private boolean hasRole(String role){
        SecurityContext context = SecurityContextHolder.getContext();
        
        if(context == null){
            return false;
        }
        
        Authentication auth = context.getAuthentication();
        
        if(auth == null){
            return false;
        }
        
        Collection<? extends GrantedAuthority> authoritys = auth.getAuthorities();
        
        //return authoritys.contains(new SimpleGrantedAuthority(role));
        
        
        for(GrantedAuthority authority : authoritys){
            LOGGER.info("ROLES USER: "+authority.getAuthority());
            if(role.equals(authority.getAuthority())){
                LOGGER.info("Hello user ".concat(auth.getName()).concat(" your role is ".concat(authority.getAuthority())));
                return true;
            }
        }
        
        return false;
        
    }
    
}
