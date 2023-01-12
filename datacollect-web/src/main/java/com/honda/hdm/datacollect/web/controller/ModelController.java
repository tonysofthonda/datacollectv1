package com.honda.hdm.datacollect.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.honda.hdm.datacollect.model.entity.DcModel;
import com.honda.hdm.datacollect.service.domain.impl.DcModelService;


@Controller
@RequestMapping("model")
public class ModelController {

	private static final Logger LOGGER = LogManager.getLogger(ModelController.class);

	@Autowired
	private DcModelService modelService;

	@ModelAttribute
	public void init(Model model) {
	}

	@GetMapping(path = { "list" })
	@PreAuthorize("hasPermission('models', 'view')")
	public String list(Model model) {
		List<DcModel> modelList = modelService.findAll();
		model.addAttribute("modelList", modelList);
		return "model/list";
	}

	@GetMapping(path = { "add" })
	@PreAuthorize("hasPermission('models', 'create')")
	public String add(Model model) {
		model.addAttribute("modelForm", new DcModel());
		return "model/form";
	}
	
	@PostMapping(path = {"add"})
	@PreAuthorize("hasPermission('models', 'create')")
    public ModelAndView add(@ModelAttribute("modelForm") @Validated DcModel modelForm, BindingResult result, Model model, final RedirectAttributes ra) {       
		if(modelService.findOneByCodeAndYear(modelForm.getCode(), modelForm.getYear()) != null) {
			model.addAttribute("error", "There is an existing model code for year " + modelForm.getYear());
			return new ModelAndView("model/form");
		}
		
		modelService.save(modelForm);
        
		ra.addFlashAttribute("message", "ModelValid created successfully");
        return new ModelAndView("redirect:/model/list");
    }
	
    @GetMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('models', 'edit')")
    public String edit(Model model, @PathVariable("id") Long id, final RedirectAttributes ra) {
    	
    	DcModel foundModel = modelService.findOne(id);
    	if(foundModel == null) {
    		ra.addFlashAttribute("error", "ModelValid does not exists");
            return "redirect:/model/list";
    	}
    	
        model.addAttribute("edit", true);
        model.addAttribute("modelForm", foundModel);

        return "model/form";
    }
    
    @PostMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('models', 'edit')")
    public ModelAndView edit(@ModelAttribute("modelForm") @Validated DcModel modelForm, @PathVariable("id") Long id, Model model, final RedirectAttributes ra) {       
    	DcModel foundModel = modelService.findOneByCodeAndYear(modelForm.getCode(), modelForm.getYear());
    	
    	if(foundModel != null && !foundModel.getId().equals(id)) {
			model.addAttribute("error", "There is an existing model code for year " + modelForm.getYear());
			return new ModelAndView("model/form");
		}
		
		modelService.save(modelForm);
        
		ra.addFlashAttribute("message", "ModelValid updated successfully");
        return new ModelAndView("redirect:/model/list");
    }
    
    @PostMapping(path = { "disable/{id}" })
    @PreAuthorize("hasPermission('models', 'change_status')")
	public  ResponseEntity<?> disable(@PathVariable("id") Long id, final RedirectAttributes ra) {
    	if(modelService.findOne(id) == null){
    		new ResponseEntity<>("ModelValid does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
	   	 }   	 
		 modelService.disable(id);
		 return new ResponseEntity<>("ModelValid disabled successfully", HttpStatus.OK);
	}
    
    @PostMapping(path = { "enable/{id}" })
    @PreAuthorize("hasPermission('models', 'change_status')")
	public  ResponseEntity<?> enable(@PathVariable("id") Long id, final RedirectAttributes ra) {
    	 if(modelService.findOne(id) == null){
    		 new ResponseEntity<>("ModelValid does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
    	 }    	 
		 modelService.enable(id);
		 return new ResponseEntity<>("ModelValid enabled successfully", HttpStatus.OK);
	}
}
