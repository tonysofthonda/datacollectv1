/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author VJC80439
 */
@Controller
@RequestMapping("login")
public class LoginController {
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model, Principal principal, RedirectAttributes flash){
        
        if(principal != null){
            flash.addFlashAttribute("info", "Already have been started session previously");
            return "redirect:/";
        }
        
        if(error != null){
            model.addAttribute("error", "User name or password are incorrect, please try again");
        }
        
        if(logout != null){
            model.addAttribute("logout", "You have closed session correctly");
        }
        
        return "login/login";
    }
}
