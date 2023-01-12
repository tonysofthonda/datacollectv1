/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.FetchType;
import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerGroupDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcDealerGroupService;

import java.math.BigDecimal;
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
@RequestMapping("/dealer-group")
public class DealerGroupController {

    @Autowired
    IDcDealerGroupService groupService;

    @GetMapping("/list")
    public ResponseEntity<?> listDealerGroups(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @RequestParam(name = "fetchType", defaultValue = "lazy") String fetchType) {
        if (fetchType.equals(FetchType.LAZY.toString())) {
            return new ResponseEntity<>(groupService.findByRecordStatusIdDto(DcRecordStatusEnum.ENABLED, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
        } else if (fetchType.equals(FetchType.EAGER.toString())) {
            return new ResponseEntity<>(groupService.findAllDto(), HttpStatus.OK);
        } else {
            Model response = new ExtendedModelMap();
            response.addAttribute("msg", "fetchType required as request param");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDealerGroup(@PathVariable BigDecimal id) {
        DcDealerGroupDto dealerGroup = groupService.findOneDto(id);
        if (dealerGroup == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Dealer Group not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dealerGroup, HttpStatus.OK);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterDealerGroup(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(groupService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDealerGroup(@RequestBody @Valid DcDealerGroupDto dealerGroup, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcDealerGroupDto newDealerGroup = groupService.saveDto(dealerGroup);
            response.addAttribute("dealerGroup", newDealerGroup);
            response.addAttribute("msg", "Dealer Group Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Model> updateDealerGroup(@PathVariable BigDecimal id, @RequestBody @Valid DcDealerGroupDto dealerGroup, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcDealerGroupDto editedDealerGroup = groupService.updateDto(dealerGroup, id);
            if (editedDealerGroup == null) {
                response.addAttribute("friendlyError", new FriendlyError("Dealer Group not found", "warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("dealerGroup", editedDealerGroup);
            response.addAttribute("msg", "Dealer Group Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disableDealerGroup(@PathVariable BigDecimal id) {
        Model response = new ExtendedModelMap();
        DcDealerGroup dealerGroup = groupService.findOne(id);
        if (dealerGroup == null) {
            response.addAttribute("friendlyError", new FriendlyError("Dealer Group not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (dealerGroup.getDcDealerList().size() > 0) {
            response.addAttribute("friendlyError", new FriendlyError("This Dealer Group cant be disabled because have dealers", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        groupService.disable(id);
        response.addAttribute("msg", "Dealer Group Disabled Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
