/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcDealerGroupRepository extends IBaseStatusableRepository<DcDealerGroup, BigDecimal> {

    DcDealerGroup findByName(@Param("name") String name);

    @Query("select dc from DcDealerGroup dc where (dc.name like %?1%) and dc.dcRecordStatusId = 1")
    public Page<DcDealerGroup> findAllByTerm(String term, Pageable pageable);

}
