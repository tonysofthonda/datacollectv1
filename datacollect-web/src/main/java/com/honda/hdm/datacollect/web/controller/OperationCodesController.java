package com.honda.hdm.datacollect.web.controller;

import java.math.BigDecimal;
import java.util.List;

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

import com.honda.hdm.datacollect.model.entity.DcOperationCode;
import com.honda.hdm.datacollect.model.enums.ServiceTypeEnum;
import com.honda.hdm.datacollect.service.domain.impl.DcOperationCodeService;

@Controller
@RequestMapping("operationcodes")
public class OperationCodesController {

	@Autowired
	private DcOperationCodeService dcOperationCodeService;
	
	@GetMapping(path = { "list" })
	@PreAuthorize("hasPermission('operationcodes', 'view')")
	public String list(Model model) throws Exception {
		List<DcOperationCode> dcOperationCodeList = dcOperationCodeService.findDcOperationCodes();
		model.addAttribute("operationCodeList",dcOperationCodeList);
		return "operationcodes/list";
	}
	
	@GetMapping(path = {"add"})
	@PreAuthorize("hasPermission('operationcodes', 'create')")
	public String add(Model model) {
		model.addAttribute("service", ServiceTypeEnum.values());
		model.addAttribute("operationCodeForm", new DcOperationCode());
		return "operationcodes/form";
		
	}
	
	@PostMapping(path = {"add"})
	@PreAuthorize("hasPermission('operationcodes', 'create')")
	public ModelAndView addOperationCodes(@ModelAttribute("operationCodeForm") @Validated DcOperationCode dcOperationCode,
			BindingResult result, Model model, final RedirectAttributes ra) throws Exception{
		if (dcOperationCodeService.findOneByCode(dcOperationCode.getCode()) != null) {
			model.addAttribute("error", "The operation code already exist");
			return new ModelAndView("operationcodes/form");
		}
		dcOperationCodeService.save(dcOperationCode);
		ra.addFlashAttribute("message", "Operation code created successfully");
		return new ModelAndView("redirect:/operationcodes/list");
	}
	
	 @GetMapping(path = {"edit/{id}"})
	 @PreAuthorize("hasPermission('operationcodes', 'edit')")
	 public String edit(Model model, @PathVariable("id") BigDecimal id, final RedirectAttributes ra) {
		 DcOperationCode dcOperationCode = dcOperationCodeService.findOne(id);
		 if (dcOperationCode == null) {
			ra.addFlashAttribute("error","Operation code not exists");
			return "redirect:/operationcodes/list";
		 }
		 if(dcOperationCode.getServiceType() != null) {
			 dcOperationCode.setServiceType(ServiceTypeEnum.findByService(dcOperationCode.getServiceType()).name()); 
		 }
		 
		 model.addAttribute("edit",true);
		 model.addAttribute("service", ServiceTypeEnum.values());
		 model.addAttribute("operationCodeForm",dcOperationCode);
		 return "operationcodes/form";
	 }

	 @PostMapping(path = {"edit/{id}"})
	 @PreAuthorize("hasPermission('operationcodes', 'edit')")
	 public ModelAndView edit(@ModelAttribute("operationCodeForm") @Validated  DcOperationCode dcOperationCodeForm ,
			 @PathVariable("id") BigDecimal id, Model model, final RedirectAttributes ra) throws Exception{
		 DcOperationCode dcOperationCode = dcOperationCodeService.findOneByCode(dcOperationCodeForm.getCode());
		
		 if (dcOperationCode != null && !dcOperationCode.getId().equals(id)) {
			 model.addAttribute("error", "The operation code already exist");
			 return new ModelAndView("operationcodes/form");
		}
		 dcOperationCodeService.save(dcOperationCodeForm);
		 ra.addFlashAttribute("message", "Operation code updated successfully");
	     return new ModelAndView("redirect:/operationcodes/list");
		 
	 }
	
	
}
