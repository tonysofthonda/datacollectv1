/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author VJC80439
 */
@Repository
public interface DcDealerRepository extends IBaseStatusableRepository<DcDealer, Long> {

    public DcDealer findOneByDealerNumberIgnoreCase(String dealerNumber);

    public DcDealer findOneByNameIgnoreCase(String name);

    public Long countByDcDealerGroup(DcDealerGroup dealerGroup);

    @Query("FROM DcDealer dealer " +
            "WHERE dealer.dealerNumber LIKE %?1% OR " +
            "dealer.dcDealerGroup.name LIKE %?1% OR " +
            "dealer.dcTerchief.firstName LIKE %?1% OR dealer.dcTerchief.lastName LIKE %?1% OR dealer.dcTerchief.motherLast LIKE %?1%")
    public Page<DcDealer> findAllByTerm(String term, Pageable pageable);

}
