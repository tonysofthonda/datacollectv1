/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcVinModel;
import com.honda.hdm.datacollect.repository.DcVinModelRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author VJ082361
 */
@Service
public class DcVinModelService extends RecordStatusableService<DcVinModel, BigDecimal>{
    private static final Logger LOGGER = LogManager.getLogger(DcTerchiefService.class);
    
    @Autowired
    private DcVinModelRepository repository;
    
    public DcVinModel findOneByModelIdAndYear(String modelId, String modelYear) {
        return repository.findOneByModelIdAndYear(modelId, modelYear);
    }

    @Override
    public DcVinModel update(DcVinModel obj, BigDecimal id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
