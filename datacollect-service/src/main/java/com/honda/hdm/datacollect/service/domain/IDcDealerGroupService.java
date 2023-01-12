/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerGroupDto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author VJC80587
 */
public interface IDcDealerGroupService extends IRecordStatusableService<DcDealerGroup, BigDecimal>,
        IDtoService<DcDealerGroupDto, BigDecimal> {

    public List<DcDealerGroupDto> findAllDto();
}
