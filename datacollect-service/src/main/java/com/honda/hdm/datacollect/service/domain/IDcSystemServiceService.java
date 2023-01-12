package com.honda.hdm.datacollect.service.domain;


import com.honda.hdm.datacollect.model.entity.DcSystemService;
import com.honda.hdm.datacollect.model.entity.dto.DcSystemServiceDto;

import java.util.List;

public interface IDcSystemServiceService extends IRecordStatusableService<DcSystemService, Long>,
        IDtoService<DcSystemServiceDto, Long> {

    public List<DcSystemServiceDto> findAllDto();
}
