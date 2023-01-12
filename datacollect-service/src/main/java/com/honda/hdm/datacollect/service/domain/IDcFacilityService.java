package com.honda.hdm.datacollect.service.domain;


import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityDto;

import java.util.List;

public interface IDcFacilityService extends IRecordStatusableService<DcFacility, Long>,
        IDtoService<DcFacilityDto, Long>{


    public List<DcFacilityDto> findAllDto();

}
