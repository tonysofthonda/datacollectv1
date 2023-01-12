/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.honda.hdm.datacollect.model.entity.DcModel;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcModelRepository extends IBaseStatusableRepository<DcModel, Long>{

    public DcModel findOneByCodeIgnoreCase(String dcModelCode);
   
    public DcModel findFirstByCodeAndYear(String code, String year);

//    @Query("select dc from DcModel dc where dc.code like %?1% or dc.year like %?1% or dc.name like %?1% or dc.description like %?1% or dc.accountNumber like %?1%")
//    public Page<DcModel> findAllByTerm(String term, Pageable pageable);
    
    @Query("select dc from DcModel dc where dc.code like %?1% or dc.year like %?1% or dc.name like %?1% or dc.description like %?1%")
    public Page<DcModel> findAllByTerm(String term, Pageable pageable);
    
}
