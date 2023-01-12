package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.IDcStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    IDcStateService stateService;

    @GetMapping("/list")
    public ResponseEntity<?> listStates(){
        return new ResponseEntity<>(stateService.findAllDto(), HttpStatus.OK);
    }
}
