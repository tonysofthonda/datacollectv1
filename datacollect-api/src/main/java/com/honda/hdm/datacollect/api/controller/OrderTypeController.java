/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.dto.DcOrderTypeDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcOrderTypeService;

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
@RequestMapping("/order-type")
public class OrderTypeController {

    @Autowired
    IDcOrderTypeService orderTypeService;

    @GetMapping("/list")
    public ResponseEntity<Page<DcOrderTypeDto>> listOrderTypes(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage) {
        return new ResponseEntity<>(orderTypeService.findAllDto(PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderType(@PathVariable BigDecimal id) {
        DcOrderTypeDto orderType = orderTypeService.findOneDto(id);
        if (orderType == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Order Type not found","warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderType, HttpStatus.OK);
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterOrderType(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @PathVariable String term) {
        return new ResponseEntity<>(orderTypeService.findAllByTermDto(term, PageRequest.of(page, elementsByPage)), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> findOrderTypeByCode(@PathVariable String code) {
        try {
            return new ResponseEntity<>(orderTypeService.findOneByCodeDto(code), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrderType(@RequestBody @Valid DcOrderTypeDto orderType, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcOrderTypeDto newOrderType = orderTypeService.saveDto(orderType);
            response.addAttribute("orderType", newOrderType);
            response.addAttribute("msg", "Order Type Added Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Model> updateOrderType(@PathVariable BigDecimal id, @RequestBody @Valid DcOrderTypeDto orderType, BindingResult result) {
        Model response = new ExtendedModelMap();
        if (result.hasErrors()) {
            response.addAllAttributes(Util.getAllErrorsOfRequest(result));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            DcOrderTypeDto editedOrderType = orderTypeService.updateDto(orderType, id);
            if (editedOrderType == null) {
                response.addAttribute("friendlyError", new FriendlyError("Order Type not found","warn"));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.addAttribute("orderType", editedOrderType);
            response.addAttribute("msg", "Order Type Updated Successfully");
        } catch (Exception e) {
            response.addAttribute("msg", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
