package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.dto.auth.DcMenuCategoryDto;

import java.util.List;

public interface IDcMenuCategoryService extends IDtoService<DcMenuCategoryDto, Long>{

    public List<DcMenuCategoryDto> findAllDto();
}
