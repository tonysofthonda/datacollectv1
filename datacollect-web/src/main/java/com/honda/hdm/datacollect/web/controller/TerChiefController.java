/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;
import javax.validation.Valid;

/**
 *
 * @author VJC80485
 */
@Controller
@RequestMapping("terchief")
public class TerChiefController {
    
    private static final Logger LOGGER = LogManager.getLogger(TerChiefController.class);
    
    @Autowired
    private IDcTerchiefService dcTerchiefService;
    
    @ModelAttribute
    public void init(Model model){
    }
    
    /**
     * To get a list of dealerGroups on status enabled.
     *
     * @param model
     * @return
     */
    @GetMapping(path = {"list"})
    @PreAuthorize("hasPermission('terchiefs', 'view')")
    public String list(Model model, @ModelAttribute("message") String message) {
        List<DcTerchief> terchiefList = dcTerchiefService.findByRecordStatusId(DcRecordStatusEnum.ENABLED);
        model.addAttribute("terchiefList", terchiefList);
        if(!message.isEmpty()) model.addAttribute("message", message);
        return "terchief/list";
    }
    
    /**
    *  Show form to add a Territory Chief
    *  @return ModelValid
    */
    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('terchiefs', 'create')")
    public String add(Model model) {
        
        model.addAttribute("terchiefForm", new DcTerchief());
        
        return "terchief/form";
    }
    
    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('terchiefs', 'create')")
    public ModelAndView add(@ModelAttribute("terchiefForm") @Validated DcTerchief terchiefForm, BindingResult result, Model model, final RedirectAttributes ra) {
        if (result.hasErrors()) {
           model.addAttribute("message", result.getAllErrors().toString());
           return new ModelAndView("terchief/form");
        }        
               
        dcTerchiefService.save(terchiefForm);
        ra.addFlashAttribute("message", "Terrritory Chief created successfully");
        return new ModelAndView("redirect:/terchief/list");
    }
    
    @GetMapping(path = "edit/{id}")
    @PreAuthorize("hasPermission('terchiefs', 'edit')")
    public String edit(Model model, @PathVariable("id") Long id) {        
        model.addAttribute("terchiefForm", dcTerchiefService.findOne(id) );        
        return "terchief/form";
    }
    
    @PostMapping(path = "edit/{id}")
    @PreAuthorize("hasPermission('terchiefs', 'edit')")
    public ModelAndView edit(@ModelAttribute("terchiefForm") @Validated DcTerchief terchiefForm, BindingResult result, Model model, @PathVariable("id") Long id, RedirectAttributes ra) {                
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return new ModelAndView("terchief/form");
        }        
        terchiefForm.setDcContactList(dcTerchiefService.getOne(terchiefForm.getId()).getDcContactList());
        dcTerchiefService.save(terchiefForm);
        ra.addFlashAttribute("message", "Terrritory Chief updated successfully");
        return new ModelAndView("redirect:/terchief/list");
    }
    
    /**
     * Delete a record from database
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "delete/{id}")
    @PreAuthorize("hasPermission('terchiefs', 'delete')")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.dcTerchiefService.delete(id);
        return new ModelAndView(new RedirectView("/terchief/list", true));
    }
    
    /**
     * Perform soft-delete of a specified record
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "disable/{id}")
    @PreAuthorize("hasPermission('terchiefs', 'change_status')")
    public ModelAndView disable(@PathVariable("id") Long id) {
        this.dcTerchiefService.disable(id);
        return new ModelAndView(new RedirectView("/terchief/list", true));
    }
}
