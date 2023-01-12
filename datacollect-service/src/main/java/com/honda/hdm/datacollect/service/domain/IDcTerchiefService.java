/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.model.entity.dto.DcTerchiefDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDcTerchiefService extends IRecordStatusableService<DcTerchief, Long>,
        IDtoService<DcTerchiefDto, Long> {

    public List<DcContact> getEnabledContacts(Long terchiefId);

    public Page<DcContact> getEnabledContacts(Long terchiefId, Pageable pageable);

    public List<DcTerchiefDto> findAllDto();

    public Page<DcContactDto> getEnabledContactsDto(Long terchiefId, Pageable pageable);

    public Page<DcContactDto> filterContactsByTerm(Long idTerchief, String term, Pageable pageable);

    public DcContactDto saveContact(Long idTerchief, DcContactDto contact);

}
