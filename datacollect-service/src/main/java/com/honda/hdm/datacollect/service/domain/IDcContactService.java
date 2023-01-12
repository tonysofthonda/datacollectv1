/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;

/**
 *
 * @author VJC80587
 */
public interface IDcContactService extends IRecordStatusableService<DcContact, Long>,
        IDtoService<DcContactDto, Long> {

}
