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
import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.service.domain.impl.DcFacilityService;

/**
 *
 * @author VJC80485
 */
@Controller
@RequestMapping("facility")
public class FacilityController {
    
    private static final Logger LOGGER = LogManager.getLogger(FacilityController.class);
    
    @Autowired
    private DcFacilityService dcFacilityService;
    
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
    @PreAuthorize("hasPermission('facilities', 'view')")
    public String list(Model model, @ModelAttribute("message") String message) {
        List<DcFacility> FacilityList = dcFacilityService.findByRecordStatusId(DcRecordStatusEnum.ENABLED);
        model.addAttribute("facilityList", FacilityList);
        if(!message.isEmpty()) model.addAttribute("message", message);
        return "facility/list";
    }
    
    /**
     * Delete a record from database
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "delete/{id}")
    @PreAuthorize("hasPermission('facilities', 'delete')")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.dcFacilityService.delete(id);
        return new ModelAndView(new RedirectView("/facility/list", true));
    }
    
    /**
     * Perform soft-delete of a specified record
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "disable/{id}")
    @PreAuthorize("hasPermission('facilities', 'change_status')")
    public ModelAndView disable(@PathVariable("id") Long id) {
        this.dcFacilityService.disable(id);
        return new ModelAndView(new RedirectView("/facility/list", true));
    }
    
    /**
    *  Show form to add a Territory Chief
    *  @return ModelValid
    */
    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('facilities', 'create')")
    public String add(Model model) {
        
        model.addAttribute("facilityForm", new DcFacility());
        
        return "facility/form";
    }
    
    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('facilities', 'create')")
    public ModelAndView add(@ModelAttribute("facilityForm") @Validated DcFacility facilityForm, BindingResult result, Model model, final RedirectAttributes ra) {
        boolean hasErrors = false;
        
        if(facilityForm.getConcept().isEmpty()){
            hasErrors = true;
            result.rejectValue("concept","error.concept", "Facility concept can't be empty");
        }
        if(facilityForm.getConcept().length() > 50){
           hasErrors = true; 
           result.rejectValue("concept","error.concept", "Facility concept length can't be longer than 50 characters");
        }    
        if(facilityForm.getDescription().length() > 100){
           hasErrors = true; 
           result.rejectValue("description","error.description", "Facility description length can't be longer than 100 characters");
        } 
        if(hasErrors == false && dcFacilityService.findIfExistByConcept(facilityForm.getConcept()) != null ){
            hasErrors = true; 
            result.rejectValue("concept","error.concept", "There is already a facility with the same name, please try with a different one");
        }
        
        if(hasErrors){
            return new ModelAndView("facility/form");
        }
        
        dcFacilityService.save(facilityForm);
        ra.addFlashAttribute("message", "Facility created successfully");
        return new ModelAndView("redirect:/facility/list");
    }
    
    @GetMapping(path = "edit/{id}")
    @PreAuthorize("hasPermission('facilities', 'edit')")
    public String edit(Model model, @PathVariable("id") Long id) {        
        model.addAttribute("facilityForm", dcFacilityService.findOne(id) );        
        return "facility/form";
    }
    
    @PostMapping(path = "edit/{id}")
    @PreAuthorize("hasPermission('facilities', 'edit')")
    public ModelAndView edit(@ModelAttribute("facilityForm") @Validated DcFacility facilityForm, BindingResult result, Model model, @PathVariable("id") Long id, RedirectAttributes ra) {                
        boolean hasErrors = false;
        
        if(facilityForm.getConcept().isEmpty()){
            hasErrors = true;
            result.rejectValue("concept","error.concept", "Facility concept can't be empty");
        }
        if(facilityForm.getConcept().length() > 50){
            hasErrors = true; 
            result.rejectValue("concept","error.concept", "Facility concept length can't be longer than 50 characters");
        }    
        if(facilityForm.getDescription().length() > 100){
            hasErrors = true; 
            result.rejectValue("description","error.description", "Facility description length can't be longer than 100 characters");
        } 
        if(hasErrors == false){
            DcFacility foundFacility = dcFacilityService.findIfExistByConcept(facilityForm.getConcept());
            if(foundFacility != null && !foundFacility.getId().equals(facilityForm.getId())){
                hasErrors = true;
                result.rejectValue("concept","error.concept", "There is already a facility with the same name, please try with a different one");
            }
        }
        
        if(hasErrors){
            return new ModelAndView("facility/form");
        }
        
        dcFacilityService.save(facilityForm);
        ra.addFlashAttribute("message", "Facility updated successfully");
        return new ModelAndView("redirect:/facility/list");
    }
}
