package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.IDcSystemServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system-service")
public class SystemServiceController {
    @Autowired
    IDcSystemServiceService systemServiceService;

    @GetMapping("/list")
    public ResponseEntity<?> listSystemService(){
        return new ResponseEntity<>(systemServiceService.findAllDto(), HttpStatus.OK);
    }
}
