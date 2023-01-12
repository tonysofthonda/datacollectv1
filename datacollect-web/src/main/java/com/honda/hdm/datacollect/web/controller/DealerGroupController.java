/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.controller;

import java.math.BigDecimal;
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
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.impl.DcDealerGroupService;
import com.honda.hdm.datacollect.web.dto.FlashMessage;
import com.honda.hdm.datacollect.web.dto.FlashMessage.FlashMessageType;

/**
 *
 * @author VJC80439
 */
@Controller
@RequestMapping("dealergroup")
public class DealerGroupController {
    
    private static final Logger LOGGER = LogManager.getLogger(DealerGroupController.class);
        
    @Autowired
    private DcDealerGroupService dcDealerGroupService;
    
    @Autowired
    private IDcDealerService dcDealerService;
    
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
    @PreAuthorize("hasPermission('dealergroups', 'view')")
    public String list(Model model) {
        List<DcDealerGroup> dealerGroupList = dcDealerGroupService.findByRecordStatusId(DcRecordStatusEnum.ENABLED);
        model.addAttribute("dealerGroupList", dealerGroupList);
        return "dealergroup/list";
    }
    
    /**
     * To show form for adding a new record
     *
     * @param model
     * @return String representing the view to resolve
     */
    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('dealergroups', 'create')")
    public String add(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("dealerGroupForm", new DcDealerGroup()); // it's better to add a form object

        return "dealergroup/form";
    }
    
    /**
     * Perform add of new record
     *
     * @param dealerGroupForm
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('dealergroups', 'create')")
    public ModelAndView add(@ModelAttribute("dealerGroupForm") @Validated DcDealerGroup dealerGroupForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        
        DcDealerGroup dcDealerGroup = dcDealerGroupService.findByName(dealerGroupForm.getName());
        if(dcDealerGroup != null){
            result.rejectValue("name", "error.name", "This dealer group name already exist.");
            return new ModelAndView("dealergroup/form");
        }

        this.dcDealerGroupService.save(dealerGroupForm);
        redirectAttributes.addFlashAttribute("dcDealerGroup", dealerGroupForm);
        redirectAttributes.addFlashAttribute("message", "Dealer Group " + dealerGroupForm.getName() + " saved successfully.");

        return new ModelAndView(new RedirectView("/dealergroup/list", true));
    }
    
    /**
     * To show form for editing an existing record
     *
     * @param model
     * @param id ID's Entity to be edited
     * @return String representing the view to resolve
     */
    @GetMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('dealergroups', 'edit')")
    public String edit(Model model, @PathVariable("id") BigDecimal id) {
        model.addAttribute("edit", false);
        model.addAttribute("dealerGroupForm", this.dcDealerGroupService.findOne(id));

        return "dealergroup/form";
    }
    
    /**
     * Perform update of an existing record
     *
     * @param dealerGroupForm
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('dealergroups', 'edit')")
    public ModelAndView edit(@ModelAttribute("dealerGroupForm") @Validated DcDealerGroup dealerGroupForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return new ModelAndView("dealergroup/form");
        }
        
        DcDealerGroup dcDealerGroup = dcDealerGroupService.findByName(dealerGroupForm.getName());
        if(dcDealerGroup != null && !dcDealerGroup.getId().toString().equals(dealerGroupForm.getId().toString())){
            result.rejectValue("name", "error.name", "This dealer group name already exist.");
            return new ModelAndView("dealergroup/form");
        }

        this.dcDealerGroupService.save(dealerGroupForm);
        redirectAttributes.addFlashAttribute("dcDealerGroup", dealerGroupForm);
        redirectAttributes.addFlashAttribute("message", "Dealer Group " + dealerGroupForm.getName() + " updated successfully.");

        return new ModelAndView(new RedirectView("/dealergroup/list", true));
    }
    
    /**
     * Delete a record from database
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "delete/{id}")
    @PreAuthorize("hasPermission('dealergroups', 'delete')")
    public ModelAndView delete(@PathVariable("id") BigDecimal id, final RedirectAttributes redirectAttributes){               
        if(this.dcDealerService.countByDealerGroup(new Long(id.toString())) > 0){
            redirectAttributes.addFlashAttribute("error_message", new FlashMessage("Delaer group has assigned dealers and it can not be removed", FlashMessageType.ERROR));
        }else{
            this.dcDealerGroupService.delete(id); 
        }        
        return new ModelAndView(new RedirectView("/dealergroup/list", true));
    }    
}
