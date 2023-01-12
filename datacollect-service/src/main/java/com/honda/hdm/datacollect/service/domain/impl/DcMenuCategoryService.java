package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcMenuCategoryDto;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcMenuCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.honda.hdm.datacollect.model.entity.DcMenuCategory;
import com.honda.hdm.datacollect.repository.DcMenuCategoryRepository;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcMenuCategoryService extends BaseDomainService<DcMenuCategory, Long> implements IDcMenuCategoryService {
    
    private static final Logger LOGGER = LogManager.getLogger(DcMenuCategoryService.class);
    
    @Autowired
    DcMenuCategoryRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcMenuCategoryDto saveDto(DcMenuCategoryDto dto) {
        return dtoConverter.convertMenuCategory(save(modelConverter.convertMenuCategory(dto)));
    }

    @Override
    public DcMenuCategoryDto updateDto(DcMenuCategoryDto dto, Long id) {
        DcMenuCategory menuCategory = findOne(id);
        if(menuCategory == null){
            return null;
        }
        menuCategory.setName(dto.getName());
        menuCategory.setOrder(dto.getOrder());
        return saveDto(dtoConverter.convertMenuCategory(menuCategory));
    }

    @Override
    public DcMenuCategoryDto findOneDto(Long id) {
        DcMenuCategory menuCategory = findOne(id);
        if(menuCategory == null){
            return null;
        }
        return dtoConverter.convertMenuCategory(menuCategory);
    }

    @Override
    public List<DcMenuCategoryDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertMenuCategory).collect(Collectors.toList());
    }

    @Override
    public Page<DcMenuCategoryDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertMenuCategory);
    }

    @Override
    public Page<DcMenuCategoryDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcMenuCategoryDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }
}
