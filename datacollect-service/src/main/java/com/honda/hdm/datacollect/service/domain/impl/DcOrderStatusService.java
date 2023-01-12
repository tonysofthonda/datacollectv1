/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcOrderStatus;
import com.honda.hdm.datacollect.repository.DcOrderStatusRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author VJC80439
 */
@Service
public class DcOrderStatusService extends RecordStatusableService<DcOrderStatus, BigDecimal>{
    
    private static final Logger LOGGER = LogManager.getLogger(DcOrderStatusService.class);
    
    @Autowired
    DcOrderStatusRepository repository;

    @Override
    public DcOrderStatus update(DcOrderStatus obj, BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
