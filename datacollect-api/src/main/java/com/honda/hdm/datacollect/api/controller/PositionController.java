package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.IDcPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    IDcPositionService positionService;

    @GetMapping("/list")
    public ResponseEntity<?> listPositions() {
        return new ResponseEntity<>(positionService.findAllDto(), HttpStatus.OK);
    }

    @GetMapping("/job-id/{jobId}")
    public ResponseEntity<?> findPositionByJobId(@PathVariable String jobId) {
        return new ResponseEntity<>(positionService.findOneByJobIdDto(jobId), HttpStatus.OK);
    }
}
