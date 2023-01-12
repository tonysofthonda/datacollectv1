/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.view.RedirectView;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcCity;
import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;
import com.honda.hdm.datacollect.service.domain.impl.DcCityService;
import com.honda.hdm.datacollect.service.domain.impl.DcDealerGroupService;
import com.honda.hdm.datacollect.service.domain.impl.DcFacilityService;
import com.honda.hdm.datacollect.service.domain.impl.DcFacilityXDealerService;
import com.honda.hdm.datacollect.service.domain.impl.DcStateService;
import com.honda.hdm.datacollect.service.domain.impl.DcWorkshopService;
import com.honda.hdm.datacollect.web.dto.DealerFacilityDto;
import com.honda.hdm.datacollect.web.mapper.DealerFacilityMapper;

/**
 *
 * @author VJC80439
 */
@Controller
@RequestMapping("dealer")
public class DealerController {
    
    private static final Logger LOGGER = LogManager.getLogger(DealerController.class);
        
    @Autowired
    private IDcDealerService dcDealerService;
    
    @Autowired
    private DcStateService dcStateService;
    
    @Autowired
    private DcCityService dcCityService;
    
    @Autowired
    private DcDealerGroupService dcDealerGroupService;
    
    @Autowired
    private IDcTerchiefService dcTerchiefService;
    
    @Autowired
    private DcFacilityXDealerService dcFacilityXDealerService;
    
    @Autowired
    private DcFacilityService dcFacilityService;
    
    @Autowired 
    private DealerFacilityMapper dealerFacilityMapper;
    
    @Autowired 
    private DcWorkshopService dcWorkshopService;
    
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
    @PreAuthorize("hasPermission('dealers', 'view')")
    public String list(Model model) {
        List<DcDealer> dealerList = dcDealerService.findAll();
        model.addAttribute("dealerList", dealerList);
        return "dealer/list";
    }
    
    /**
     * To show form for adding a new record
     *
     * @param model
     * @return String representing the view to resolve
     */
    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('dealers', 'create')")
    public String add(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("dealerData", new DcDealer());
        model.addAttribute("stateList", dcStateService.findAll());
        model.addAttribute("dealerGroupList", dcDealerGroupService.findAll());
        model.addAttribute("terchiefList", dcTerchiefService.findByRecordStatusId(DcRecordStatusEnum.ENABLED));     
        model.addAttribute("workshopList", dcWorkshopService.findAll());     
        
        return "dealer/form";
    }
    
    /**
     * Perform add of new record
     *
     * @param dealerData
     * @param result
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('dealers', 'create')")
    public String add(@ModelAttribute("dealerData") @Validated DcDealer dealerData, BindingResult result,final Model model, final RedirectAttributes redirectAttributes) {
        Boolean hasErrors = false;
        Map<String, String> validations = validateDealerForm(dealerData);
        if(validations != null && validations.size() > 0){
            hasErrors = true;
            validations.keySet().forEach((field) -> {
                result.rejectValue(field, "error." + field, validations.get(field));
            });
        }   
        
        if (hasErrors == true){
            model.addAttribute("stateList", dcStateService.findAll());
            model.addAttribute("dealerGroupList", dcDealerGroupService.findAll());
            model.addAttribute("terchiefList", dcTerchiefService.findByRecordStatusId(DcRecordStatusEnum.ENABLED));     
            model.addAttribute("workshopList", dcWorkshopService.findAll()); 

            return "dealer/form";
        }

        this.dcDealerService.save(dealerData);
        redirectAttributes.addFlashAttribute("message", "Dealer " + dealerData.getDealerNumber()+ " saved successfully.");

        return "redirect:list";
    }
    
    public static class DealerCityDTO{
        public BigInteger id;
        public String name;
        
        public DealerCityDTO(){}
        
        public DealerCityDTO(BigInteger id, String name){
            this.id = id;
            this.name = name;
        }
    }
    
    @GetMapping(path = "getCitiesByState/{state_id}", produces = "application/json")
    public ResponseEntity<?> getCitiesByState (@PathVariable("state_id") Long id) {
        List<DcCity> dcCityList = dcCityService.findByDcStateId(new BigDecimal(id)); 
        List<DealerCityDTO> cityList = new ArrayList<>();
        dcCityList.stream().forEach(city -> {
            cityList.add(new DealerCityDTO(city.getId().toBigInteger(), city.getName()));
        });
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
    
    @GetMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('dealers', 'edit')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("dealerData", this.dcDealerService.findOne(id));
        model.addAttribute("stateList", dcStateService.findAll());
        model.addAttribute("dealerGroupList", dcDealerGroupService.findAll());
        model.addAttribute("terchiefList", dcTerchiefService.findByRecordStatusId(DcRecordStatusEnum.ENABLED));
        model.addAttribute("facilityList", dcFacilityService.findAll());
        model.addAttribute("workshopList", dcWorkshopService.findAll()); 
        
        return "dealer/form";
    }
    
    @GetMapping(path = {"getDealerFacilities/{id}"})
    public ResponseEntity<?> getDealerFacilities(@PathVariable("id") Long id) {     
        return new ResponseEntity<>(dealerFacilityMapper.map(dcFacilityXDealerService.findAllByDealerId(id)), HttpStatus.OK);
    }
    
    @PostMapping(path = "saveFacility", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('dealers', 'add') or hasPermission('dealers', 'edit')")
    public ResponseEntity<?> saveFacility(DealerFacilityDto dealerFacilityDto) { 
        DcFacilityXDealer dealerFacility = dealerFacilityMapper.map(dealerFacilityDto);
        dcFacilityXDealerService.save(dealerFacility);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping(path = "deleteFacility", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFacility(DealerFacilityDto dealerFacilityDto) { 
        dcFacilityXDealerService.delete(dealerFacilityDto.getFacilityId(), dealerFacilityDto.getDealerId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('dealers', 'edit')")
    public String edit(@ModelAttribute("dealerData") @Validated DcDealer dealerData,  BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        Boolean hasErrors = false;
        Map<String, String> validations = validateDealerForm(dealerData);
        if(validations != null && validations.size() > 0){
            hasErrors = true;
            validations.keySet().forEach((field) -> {
                result.rejectValue(field, "error." + field, validations.get(field));
            });
        }        
        
        if (hasErrors == true){
            model.addAttribute("stateList", dcStateService.findAll());
            model.addAttribute("dealerGroupList", dcDealerGroupService.findAll());
            model.addAttribute("terchiefList", dcTerchiefService.findByRecordStatusId(DcRecordStatusEnum.ENABLED));     
            model.addAttribute("workshopList", dcWorkshopService.findAll()); 

            return "dealer/form";
        }
        
        dealerData.setDcContactList(dcDealerService.getOne(dealerData.getId()).getDcContactList());
        this.dcDealerService.save(dealerData);
        redirectAttributes.addFlashAttribute("message", "Dealer " + dealerData.getDealerNumber() + " updated successfully.");

        return "redirect:/dealer/list";
    }
    
    
    private Map<String, String> validateDealerForm(DcDealer dealerFormData){
        Map<String, String> result = new HashMap<>();
        
        DcDealer foundDealer;
        if(dealerFormData.getDealerNumber() == null || dealerFormData.getDealerNumber().equals("")){
            result.put("dealerNumber", "Dealer number can not be empty");
        }else{    
            foundDealer = dcDealerService.findOneByDealerNumber(dealerFormData.getDealerNumber());
            if(foundDealer != null && !foundDealer.getId().equals(dealerFormData.getId())){
                result.put("dealerNumber", "Dealer number already exist");
            }
        }
        if (dealerFormData.getName() == null || dealerFormData.getName().equalsIgnoreCase("")){
            result.put("name", "Dealer name can not be empty");
        }else{
            foundDealer = dcDealerService.findOneByName(dealerFormData.getName());
            if(foundDealer != null && !foundDealer.getId().equals(dealerFormData.getId())){
                result.put("name", "Dealer name already exist");
            }
        }
        if (dealerFormData.getDcCityId() == null || dealerFormData.getDcCityId().getDcStateId() == null){
            result.put("dcCityId.dcStateId", "State field can not be empty");
        }
        if (dealerFormData.getDcCityId() == null || dealerFormData.getDcCityId().getId() == null){
            result.put("dcCityId", "City field can not be empty");
        }   
        if (dealerFormData.getDcDealerGroup()== null || dealerFormData.getDcDealerGroup().getId() == null){
            result.put("dcDealerGroup", "Dealer Group type field can not be empty");
        }
        if (dealerFormData.getDcTerchief()== null || dealerFormData.getDcTerchief().getId() == null){
            result.put("dcTerchief", "Territory Chief type field can not be empty");
        }
        if (dealerFormData.getDcWorkshopId() == null || dealerFormData.getDcWorkshopId().getId() == null){
            result.put("dcWorkshopId", "Workshop type field can not be empty");
        }
        
        return result;
    }
    
    @PostMapping(path = { "disable/{id}" })
    @PreAuthorize("hasPermission('dealers', 'change_status')")
	public  ResponseEntity<?> disable(@PathVariable("id") Long id, final RedirectAttributes ra) {
    	if(dcDealerService.findOne(id) == null){
    		new ResponseEntity<>("Dealer does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
	   	 }   	 
    	dcDealerService.disable(id);
		 return new ResponseEntity<>("Dealer disabled successfully", HttpStatus.OK);
	}
    
    @PostMapping(path = { "enable/{id}" })
    @PreAuthorize("hasPermission('dealers', 'change_status')")
	public  ResponseEntity<?> enable(@PathVariable("id") Long id, final RedirectAttributes ra) {
    	 if(dcDealerService.findOne(id) == null){
    		 new ResponseEntity<>("Dealer does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
    	 }    	 
    	 dcDealerService.enable(id);
		 return new ResponseEntity<>("Dealer enabled successfully", HttpStatus.OK);        	 
	}
    
}
