/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDcDealerService extends IRecordStatusableService<DcDealer, Long>, IDtoService<DcDealerDto, Long> {
    public DcDealer findOneByDealerNumber(String dealerNumber);

    public DcDealer findOneByName(String name);

    public Long countByDealerGroup(Long dealerGroupId);

    public List<DcContact> getEnabledContacts(Long dealerId);

    public Page<DcContact> getEnabledContacts(String dealerNumber, Pageable pageable);

    public List<DcContact> getEnabledContacts(String dealerNumber);

    public List<DcContact> getEnabledContacts(DcDealer dealer);

    public DcDealerDto findOneByDealerNumberDto(String dealerNumber);

    public DcDealerDto findOneByNameDto(String name);

    public DcDealerDto changeStatus(Boolean status, Long id);

}
