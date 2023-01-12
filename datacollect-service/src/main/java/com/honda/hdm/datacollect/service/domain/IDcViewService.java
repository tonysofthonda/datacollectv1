package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.dto.auth.DcViewDto;

import java.util.List;

public interface IDcViewService extends IDtoService<DcViewDto, Long>{

    public List<DcViewDto> findAllDto();
}
