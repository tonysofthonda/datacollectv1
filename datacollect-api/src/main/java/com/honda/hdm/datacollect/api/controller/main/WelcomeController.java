/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.api.controller.main;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@RestController
public class WelcomeController {
               
    private static final Logger LOGGER = LogManager.getLogger(WelcomeController.class);
    
    @GetMapping(path={"/","/welcome","/home", "/dashboard"})
    public String home(Model model, HttpServletRequest request) {                              
        return "{msg : \"Data Collect API is running!\"}";
    }
    
}
