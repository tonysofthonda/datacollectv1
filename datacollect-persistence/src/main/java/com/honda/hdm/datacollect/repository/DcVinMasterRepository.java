/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcVinMaster;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author VJ082361
 */
@Repository
public interface DcVinMasterRepository extends IBaseRepository<DcVinMaster, String> {
    
    @Query("select e from #{#entityName} e where e.vin = (:vin)")
    DcVinMaster findOneByVin(@Param("vin") String vin);
}
