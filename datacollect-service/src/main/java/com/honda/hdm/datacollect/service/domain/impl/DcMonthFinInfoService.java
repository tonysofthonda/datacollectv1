/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcMonthFinInfo;
import com.honda.hdm.datacollect.repository.DcMonthFinInfoRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class DcMonthFinInfoService extends RecordStatusableService<DcMonthFinInfo, BigDecimal>{
    
    private static final Logger LOGGER = LogManager.getLogger(DcMonthFinInfoService.class);
    
    @Autowired
    DcMonthFinInfoRepository repository;

    public DcMonthFinInfo findOneByDcDealerIdAndMonthNumberAndYearNumber(DcDealer dcDealer, BigInteger month, BigInteger year) throws Exception {
        if (dcDealer == null || month == null || year == null)
            throw new Exception("DcDealerId, month and year values are required for searching Financial Monthly record on database.");
        
        return repository.findTop1ByDcDealerIdAndMonthNumberAndYearNumber(dcDealer, month, year);
    }

    @Override
    public DcMonthFinInfo update(DcMonthFinInfo obj, BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}