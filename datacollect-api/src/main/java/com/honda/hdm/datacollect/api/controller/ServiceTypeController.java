/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.IDcServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author VJC80587
 */
@RestController
@RequestMapping("/service-type")
public class ServiceTypeController {

    @Autowired
    IDcServiceTypeService serviceTypeService;

    @GetMapping("/list")
    public ResponseEntity<?> listServiceType() {
        return new ResponseEntity<>(serviceTypeService.findAllDto(), HttpStatus.OK);
    }
}
