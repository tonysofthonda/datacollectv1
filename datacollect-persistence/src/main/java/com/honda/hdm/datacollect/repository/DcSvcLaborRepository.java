/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcSvcLabor;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcSvcLaborRepository extends IBaseStatusableRepository<DcSvcLabor, BigDecimal>{
    
}
