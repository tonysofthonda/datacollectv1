/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;
import com.honda.hdm.datacollect.service.domain.impl.DcContactService;

/**
 *
 * @author VJC80413
 */


@Controller
@RequestMapping("contact")
public class ContactController {

    private static final Logger LOGGER = LogManager.getLogger(ContactController.class);



    @Autowired
    IDcTerchiefService dcTerchiefService;

    @Autowired
    IDcDealerService dcDealerService;

    @Autowired
    private DcContactService dcContactService;


    @ModelAttribute
    public void init(Model model){

    }

    /**
     * To get a list of contacts depending of parent type Class Terchief or Dealer
     *
     * @param model
     * @return
     */
    @GetMapping(path = {"list/{parentId}/{contactType}"})
    public String list(Model model, @ModelAttribute("message") String message,@PathVariable("parentId") Long parentId, @PathVariable("contactType") String contactType) {
        List<DcContact> contactList = getContactListByType(parentId, contactType);
        model.addAttribute("parentId",parentId);
        model.addAttribute("type", contactType);
        model.addAttribute("contactForm", new DcContact());
        model.addAttribute("contactList", contactList);
        if(!message.isEmpty()) model.addAttribute("message", message);
        return "contact/form";
    }

    /**
     * Perform soft-delete of a specified record
     *
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "disable/{parentId}/{contactType}/{id}")
    public ModelAndView disable(@PathVariable("id") Long id, @PathVariable("parentId") Long parentId, @PathVariable("contactType") String contactType, RedirectAttributes ra) {
        this.dcContactService.disable(id);
        ra.addFlashAttribute("message", "Contact disabled successfully");
        return new ModelAndView("redirect:/contact/list/"+parentId+"/"+contactType);
    }

    @PostMapping(path = {"add/{parentId}/{contactType}"})
    public ModelAndView add(@ModelAttribute("contactForm") @Validated DcContact contactForm, BindingResult result, Model model, final RedirectAttributes ra, @PathVariable("parentId") Long parentId, @PathVariable("contactType") String contactType) {
        boolean hasErrors = false;
        List<DcContact> parentContactList = getContactListByType(parentId, contactType);

        if(contactForm.getEmail() == null || contactForm.getEmail().isEmpty()){
            result.rejectValue("email", "error.email", "The email must not be empty.");
            hasErrors = true;
        }else{
            String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
            Pattern pattern = Pattern.compile(emailPattern);
            String email= contactForm.getEmail();
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
              result.rejectValue("email", "error.email", "It is not a valid email.");
              hasErrors = true;
            }else{
                for(DcContact contact:parentContactList){
                   String mailSaved = contact.getEmail();
                   if(email.equals(mailSaved)){
                       result.rejectValue("email", "error.email", "Email is already beign used for another contact");
                       hasErrors = true;
                       break;
                   }
                }
            }
        }
        if(contactForm.getFirstName() == null || contactForm.getFirstName().isEmpty()){
            result.rejectValue("firstName", "error.firstName", "The first name must not be empty.");
            hasErrors = true;
        }
        if(contactForm.getLastName() == null || contactForm.getLastName().isEmpty()){
            result.rejectValue("lastName", "error.lastName", "The last name must not be empty.");
            hasErrors = true;
        }
        if(contactForm.getMotherLastName() == null || contactForm.getMotherLastName().isEmpty()){
            result.rejectValue("motherLastName", "error.motherLastName", "The mother last name must not be empty.");
            hasErrors = true;
        }
        if(hasErrors){
            ModelAndView view = new ModelAndView("contact/form");
            view.addObject("parentId",parentId);
            view.addObject("type",contactType);
            view.addObject("contactList", parentContactList);
            return view;
        }
        if(this.saveContactByType(parentId, contactType, contactForm)){
            ra.addFlashAttribute("message", "Contact created successfully");
        }
        else{
            ra.addFlashAttribute("message", "Contact was not created");
        }
        return new ModelAndView("redirect:/contact/list/"+parentId+"/"+contactType+"");
    }

    @GetMapping(path = "edit/{parentId}/{contactType}/{id}")
    public String edit(Model model, @PathVariable("parentId") Long parentId, @PathVariable("contactType") String contactType, @PathVariable("id") Long contactId) {
        List<DcContact> contactList =  getContactListByType(parentId,contactType);
        model.addAttribute("contactForm", dcContactService.findOne(contactId));
        model.addAttribute("parentId",parentId);
        model.addAttribute("type", contactType);
        model.addAttribute("contactList", contactList);
        return "contact/form";
    }

    @PostMapping(path = "edit/{parentId}/{contactType}/{id}")
    public ModelAndView edit(@ModelAttribute("contactForm") @Validated DcContact contactForm, BindingResult result, Model model, @PathVariable("parentId") Long parentId, @PathVariable("contactType") String contactType, @PathVariable("id") Long contactId, RedirectAttributes ra) {
        boolean hasErrors = false;
        List<DcContact> parentContactList = getContactListByType(parentId, contactType);

        if(contactForm.getEmail() == null || contactForm.getEmail().isEmpty()){
            result.rejectValue("email", "error.email", "The email must not be empty.");
            hasErrors = true;
        }else{
            String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(contactForm.getEmail());

            if (!matcher.matches()) {
              result.rejectValue("email", "error.email", "It is not a valid email.");
              hasErrors = true;
            }else{
                for(DcContact contact : parentContactList){
                    System.out.println("Same ID => " + Objects.equals(contactForm.getId(), contact.getId()));
                    System.out.println("Same Email => " + contactForm.getEmail().equals(contact.getEmail()));
                    if(!Objects.equals(contactForm.getId(), contact.getId()) && contactForm.getEmail().equals(contact.getEmail())){
                        result.rejectValue("email", "error.email", "Email is already beign used for another contact");
                        hasErrors = true;
                        break;
                   }
                }
            }
        }
        if(contactForm.getFirstName() == null || contactForm.getFirstName().isEmpty()){
            result.rejectValue("firstName", "error.firstName", "The first name must not be empty.");
            hasErrors = true;
        }
        if(contactForm.getLastName() == null || contactForm.getLastName().isEmpty()){
            result.rejectValue("lastName", "error.lastName", "The last name must not be empty.");
            hasErrors = true;
        }
        if(contactForm.getMotherLastName() == null || contactForm.getMotherLastName().isEmpty()){
            result.rejectValue("motherLastName", "error.motherLastName", "The mother last name must not be empty.");
            hasErrors = true;
        }
        if(hasErrors){
            ModelAndView view = new ModelAndView("contact/form");
            view.addObject("parentId", parentId);
            view.addObject("type", contactType);
            view.addObject("contactList", parentContactList);
            return view;
        }
        dcContactService.save(contactForm);
        ra.addFlashAttribute("message", "Contact updated successfully");
        return new ModelAndView("redirect:/contact/list/"+parentId+"/" + contactType + "");
    }

    private boolean saveContactByType(Long idType, String type, DcContact contactForm){
    	Object repObj;        
        switch (type) {
            case "terchief":
                repObj = dcTerchiefService.findOne(idType);
                break;
            case "dealer":
                repObj = dcDealerService.findOne(idType.longValue());
                break;
            default:
                repObj = new Object();
                break;
        }
                
        boolean saved = false;
        if (repObj instanceof DcTerchief){ 
            ((DcTerchief) repObj ).getDcContactList().add(dcContactService.save(contactForm));
            dcTerchiefService.save( (DcTerchief) repObj);
            saved = true;
        }
        else if (repObj instanceof DcDealer){ 
            ((DcDealer) repObj ).getDcContactList().add(dcContactService.save(contactForm));
              dcDealerService.save( (DcDealer) repObj);
            saved = true;
        }
        return saved;
    }

    private List<DcContact> getContactListByType(Long parentId, String type){
        List<DcContact> contactList = new ArrayList<>();
        if(type.equals("terchief")) {
            contactList = dcTerchiefService.getEnabledContacts(parentId);
        }
        else if(type.equals("dealer")) {
//            contactList = dcDealerService.getEnabledContacts(parentId.longValue()).stream().map(contactDealer->contactDealer.getContact()).collect(Collectors.toList());
        	contactList = dcDealerService.getEnabledContacts(parentId.longValue());
        }
        return contactList;
    }

}
