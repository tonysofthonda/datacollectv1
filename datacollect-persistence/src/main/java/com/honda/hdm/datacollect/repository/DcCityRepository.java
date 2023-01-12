/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcCity;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcCityRepository extends IBaseStatusableRepository<DcCity, BigDecimal>{
   
    @Query("select e from #{#entityName} e where e.dcStateId.id = (:stateId) ")
    List<DcCity> findByDcStateId(@Param("stateId") BigDecimal stateId);
}
