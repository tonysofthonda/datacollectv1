/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcServiceType;
import com.honda.hdm.datacollect.model.entity.dto.DcServiceTypeDto;
import java.util.List;

/**
 *
 * @author VJC80587
 */
public interface IDcServiceTypeService extends IRecordStatusableService<DcServiceType, Long>,
        IDtoService<DcServiceTypeDto, Long> {

    public List<DcServiceTypeDto> findAllDto();
}
