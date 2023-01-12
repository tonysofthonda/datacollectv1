/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcState;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcStateRepository extends IBaseStatusableRepository<DcState, BigDecimal>{

    @Query("SELECT e.id FROM #{#entityName} e where upper(e.name) = upper(:name)")
    public BigDecimal findOneByNameIgnoreCase(@Param("name") String name);
    
}
