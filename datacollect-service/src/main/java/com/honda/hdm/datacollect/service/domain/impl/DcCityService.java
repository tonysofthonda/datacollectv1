/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcCity;
import com.honda.hdm.datacollect.repository.DcCityRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.math.BigDecimal;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author VJC80439
 */
@Service
public class DcCityService extends RecordStatusableService<DcCity, BigDecimal>{
    
    private static final Logger LOGGER = LogManager.getLogger(DcCityService.class);
    
    @Autowired
    DcCityRepository repository;
    
    public List<DcCity> findByDcStateId(BigDecimal stateId){
        return this.repository.findByDcStateId(stateId);
    }

    @Override
    public DcCity update(DcCity obj, BigDecimal id) {
        return null;
    }
}
