package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcWorkshop;
import com.honda.hdm.datacollect.model.entity.dto.DcWorkshopDto;

import java.util.List;

public interface IDcWorkshopService extends IRecordStatusableService<DcWorkshop, Long>,
        IDtoService<DcWorkshopDto, Long>{

    public DcWorkshop findByName(String name);

    public DcWorkshopDto findByNameDto(String name);

    public List<DcWorkshopDto> findAllDto();
}
