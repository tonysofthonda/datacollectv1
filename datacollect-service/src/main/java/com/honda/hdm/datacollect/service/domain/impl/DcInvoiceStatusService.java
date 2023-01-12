/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcInvoiceStatus;
import com.honda.hdm.datacollect.repository.DcInvoiceStatusRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
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
public class DcInvoiceStatusService extends RecordStatusableService<DcInvoiceStatus, BigDecimal>{
    
    private static final Logger LOGGER = LogManager.getLogger(DcInvoiceStatusService.class);
    
    @Autowired
    DcInvoiceStatusRepository repository;

    public DcInvoiceStatus findOneByName(String name) throws Exception {
        if (StringUtils.isEmpty(name))
            throw new Exception("Status Name is required for searching a Status record into database");
        return repository.findOneByNameIgnoreCase(name);
    }

    @Override
    public DcInvoiceStatus update(DcInvoiceStatus obj, BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
