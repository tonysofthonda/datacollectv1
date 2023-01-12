/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcVinModel;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJ082361
 */
@Repository
public interface DcVinModelRepository extends IBaseStatusableRepository<DcVinModel, BigDecimal>{
    
    @Query("select e from #{#entityName} e where e.modelId = (:modelId) and e.modelYear = (:modelYear)")
    DcVinModel findOneByModelIdAndYear(@Param("modelId") String modelId, @Param("modelYear") String modelYear);

}
