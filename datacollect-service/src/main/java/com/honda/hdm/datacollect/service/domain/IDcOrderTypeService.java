/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcOrderType;
import com.honda.hdm.datacollect.model.entity.dto.DcOrderTypeDto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author VJC80587
 */
public interface IDcOrderTypeService extends IRecordStatusableService<DcOrderType, BigDecimal>,
        IDtoService<DcOrderTypeDto, BigDecimal> {

    public DcOrderType findOneByCode(String code) throws Exception;
    
    public DcOrderTypeDto findOneByCodeDto(String code) throws Exception;

    public List<DcOrderType> findDcOrderType() throws Exception;
}
