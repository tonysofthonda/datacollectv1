/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository.comm;

import com.honda.hdm.datacollect.model.entity.DcContact;
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
public interface DcContactRepository extends IBaseStatusableRepository<DcContact, Long> {
    
    DcContact findByFirstName(@Param("firstName") String firstName);
    
    DcContact findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    
    @Query("select dc from DcContact dc where (dc.firstName like %?1% or dc.lastName like %?1% or dc.motherLastName like %?1% or dc.email like %?1% or dc.phoneNumber like %?1% ) and dc.dcRecordStatusId = 1")
    public Page<DcContact> findAllByTerm(String term, Pageable pageable);
}
