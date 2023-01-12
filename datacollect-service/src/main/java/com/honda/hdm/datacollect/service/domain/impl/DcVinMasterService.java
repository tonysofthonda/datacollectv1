/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.entity.DcVinMaster;
import com.honda.hdm.datacollect.repository.DcVinMasterRepository;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author VJ082361
 */
@Service
public class DcVinMasterService extends BaseDomainService<DcVinMaster, String>{
    
    @Autowired
    private DcVinMasterRepository dcVinMasterRepository;
    
    public DcVinMaster findByVin(String vin) {
        return dcVinMasterRepository.findOneByVin(vin);
    }
}
