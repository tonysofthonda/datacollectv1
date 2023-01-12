/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcMonthFinInfo;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcMonthFinInfoRepository extends IBaseStatusableRepository<DcMonthFinInfo, BigDecimal>{

    public DcMonthFinInfo findTop1ByDcDealerIdAndMonthNumberAndYearNumber(DcDealer dcDealer, BigInteger month, BigInteger year);
    
}
