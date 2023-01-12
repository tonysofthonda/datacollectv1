/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.FetchType;
import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.model.entity.dto.DcTerchiefDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author VJC80587
 */
@RestController
@RequestMapping("/terchief")
public class TerchiefController {

    @Autowired
    private IDcTerchiefService terchiefService;

    @GetMapping("/list")
    public ResponseEntity<?> listTerchief(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @RequestParam(name = "fetchType", defaultValue = "lazy") String fetchType) {
        if (fetchType.equals(FetchType.LAZY.toString())) {
            return new ResponseEntity<>(terchiefService.findByRecordStatusIdDto(DcRecordStatusEnum.ENABLED, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
        } else if (fetchType.equals(FetchType.EAGER.toString())) {
            return new ResponseEntity<>(terchiefService.findAllDto(), HttpStatus.OK);
        } else {
            Model response = new ExtendedModelMap();
            response.addAttribute("msg", "fetchType required as request param");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTerchief(@PathVariable Long id) {
        DcTerchiefDto terchief = terchiefService.findOneDto(id);
        if (terchief == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Terchief not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(terchief, HttpStatus.OK);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterTerchief(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(terchiefService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTerchief(@RequestBody @Valid DcTerchiefDto terchief, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcTerchiefDto newTerchief = terchiefService.saveDto(terchief);
            response.addAttribute("terchief", newTerchief);
            response.addAttribute("msg", "Terchief Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Model> update(@PathVariable Long id, @RequestBody @Valid DcTerchiefDto terchief, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcTerchiefDto editedTerchief = terchiefService.updateDto(terchief, id);
            if (editedTerchief == null) {
                response.addAttribute("msg", "Terchief not found");
                FriendlyError friendlyError = new FriendlyError();
                friendlyError.setMessage("Terchief not found");
                friendlyError.setStatus("warn");
                response.addAttribute("friendlyError", friendlyError);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("terchief", editedTerchief);
            response.addAttribute("msg", "Terchief Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableTerchief(@PathVariable Long id) {
        Model response = new ExtendedModelMap();
        DcTerchief terchief = terchiefService.findOne(id);
        if (terchief == null) {
            response.addAttribute("friendlyError", new FriendlyError("Terchief not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (terchief.getDcDealerList().size() > 0) {
            response.addAttribute("friendlyError", new FriendlyError("This terchief cant be disabled because have dealers", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        terchiefService.disable(id);
        response.addAttribute("msg", "Terchief Disabled Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> getContacts(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) {
        if (terchiefService.findOne(id) == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Terchief not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(terchiefService.getEnabledContactsDto(id, PageRequest.of(page, elementsByPage)),
                HttpStatus.OK);
    }

    @GetMapping("/contacts/filter/{id}/{term}")
    public ResponseEntity<?> filterContact(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable Long id,
            @PathVariable String term) {
        if (terchiefService.findOne(id) == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Terchief not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(terchiefService.filterContactsByTerm(id, term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/contacts/add")
    public ResponseEntity<?> addContact(
            @RequestBody @Valid DcContactDto contact,
            BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAttribute("msg", "Fields Not valids");
            result.getFieldErrors().stream().forEach(error -> {
                response.addAttribute(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (contact.getTerchiefId() == null) {
            response.addAttribute("msg", "Terchief Id required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (terchiefService.findOne(contact.getTerchiefId()) == null) {
            response.addAttribute("friendlyError", new FriendlyError("Terchief not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcContactDto newContact = terchiefService.saveContact(contact.getTerchiefId(), contact);
            response.addAttribute("contact", newContact);
            response.addAttribute("msg", "Contact Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
