/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerGroupDto;
import com.honda.hdm.datacollect.repository.DcDealerGroupRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcDealerGroupService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author VJC80439
 */
@Service
public class DcDealerGroupService extends RecordStatusableService<DcDealerGroup, BigDecimal> implements IDcDealerGroupService {

    @Autowired
    DcDealerGroupRepository repository;
    
    @Autowired
    DtoConverter dtoConverter;
    
    @Autowired
    ModelConverter modelConverter;

    public DcDealerGroup findByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public DcDealerGroup update(DcDealerGroup obj, BigDecimal id) {
        Optional<DcDealerGroup> currentGroup = repository.findById(id);
        if (!currentGroup.isPresent()) {
            return null;
        }
        currentGroup.get().setName(obj.getName());
        return repository.save(currentGroup.get());
    }

    @Override
    public DcDealerGroupDto saveDto(DcDealerGroupDto dto) {
        dto.setStatus(DcRecordStatusEnum.ENABLED);
        DcDealerGroup group = modelConverter.convertDealerGroup(dto);
        DcDealerGroup newGroup = save(group);
        return dtoConverter.convertDealerGroup(newGroup);
    }

    @Override
    public DcDealerGroupDto updateDto(DcDealerGroupDto dto, BigDecimal id) {
        DcDealerGroup group = update(modelConverter.convertDealerGroup(dto), id);
        if (group == null) {
            return null;
        }
        return dtoConverter.convertDealerGroup(group);
    }

    @Override
    public DcDealerGroupDto findOneDto(BigDecimal id) {
        DcDealerGroup group = findOne(id);
        if (group == null) {
            return null;
        }
        return dtoConverter.convertDealerGroup(group);
    }

    @Override
    public List<DcDealerGroupDto> findAllDto() {
        return findByRecordStatusId(DcRecordStatusEnum.ENABLED).stream().map(dtoConverter::convertDealerGroup).collect(Collectors.toList());
    }

    @Override
    public Page<DcDealerGroupDto> findAllDto(Pageable pageable) {
        Page<DcDealerGroupDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertDealerGroup);
        return paginatedDto;
    }

    @Override
    public Page<DcDealerGroupDto> findAllByTermDto(String term, Pageable pageable) {
        Page<DcDealerGroupDto> paginatedDto = repository.findAllByTerm(term, pageable).map(dtoConverter::convertDealerGroup);
        return paginatedDto;
    }

    @Override
    public Page<DcDealerGroupDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcDealerGroupDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertDealerGroup);
        return paginatedDto;
    }

}
