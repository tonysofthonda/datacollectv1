/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DcTerchiefRepository extends IBaseStatusableRepository<DcTerchief, Long> {

    @Query("select dc from DcTerchief dc where (dc.firstName like %?1% or dc.lastName like %?1% or dc.motherLast like %?1%) and dc.dcRecordStatusId = 1")
    public Page<DcTerchief> findAllByTerm(String term, Pageable pageable);
}
