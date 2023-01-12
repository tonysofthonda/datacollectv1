/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcOrderType;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcOrderTypeRepository extends IBaseStatusableRepository<DcOrderType, BigDecimal>{

    public DcOrderType findOneByCodeIgnoreCase(String code);
    
    @Query("select dc from DcOrderType dc where dc.code like %?1% or dc.description like %?1%")
    public Page<DcOrderType> findAllByTerm(String term, Pageable pageable);
    
}
