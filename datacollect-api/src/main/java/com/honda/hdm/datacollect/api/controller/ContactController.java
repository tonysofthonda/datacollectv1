/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcContactService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author VJC80587
 */
@RestController
@RequestMapping("/contact")
public class ContactController {

	@Lazy
    @Autowired
    IDcContactService contactService;

    @PutMapping("/update")
    public ResponseEntity<Model> update(
            @RequestBody @Valid DcContactDto contact,
            BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcContactDto editedContact = contactService.updateDto(contact, contact.getId());
            if (editedContact == null) {
                response.addAttribute("friendlyError", new FriendlyError("Contact not found","warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("contact", editedContact);
            response.addAttribute("msg", "Contact Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableContact(@PathVariable Long id) {
        Model response = new ExtendedModelMap();
        DcContactDto contact = contactService.findOneDto(id);
        if (contact == null) {
            response.addAttribute("friendlyError", new FriendlyError("Contact not found","warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        contactService.disable(id);
        response.addAttribute("msg", "Contact Disabled Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
