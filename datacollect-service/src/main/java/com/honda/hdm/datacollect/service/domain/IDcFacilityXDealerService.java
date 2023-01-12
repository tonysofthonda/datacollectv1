package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityXDealerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDcFacilityXDealerService extends IDtoService<DcFacilityXDealerDto, DcFacilityXDealerPK> {
    public Page<DcFacilityXDealerDto> findAllByTermDto(Long dealerId, String term, Pageable pageable);

    public Page<DcFacilityXDealerDto> findAllDto(Long dealerId, Pageable pageable);

    public Boolean deleteById(DcFacilityXDealerPK id);

}
