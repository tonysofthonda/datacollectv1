package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.IDcViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    IDcViewService viewService;

    @GetMapping("/list")
    public ResponseEntity<?> listViews(){
        return new ResponseEntity<>(viewService.findAllDto(), HttpStatus.OK);
    }
}
