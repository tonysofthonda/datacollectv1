/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcServiceType;
import com.honda.hdm.datacollect.model.entity.dto.DcServiceTypeDto;
import com.honda.hdm.datacollect.repository.comm.DcServiceTypeRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcServiceTypeService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author VJC80587
 */
@Service
public class DcServiceTypeService extends RecordStatusableService<DcServiceType, Long> implements IDcServiceTypeService {

    @Autowired
    DcServiceTypeRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcServiceType update(DcServiceType obj, Long id) {
        Optional<DcServiceType> currentService = repository.findById(id);
        if (!currentService.isPresent()) {
            return null;
        }
        currentService.get().setName(obj.getName());
        return repository.save(currentService.get());
    }

    @Override
    public DcServiceTypeDto saveDto(DcServiceTypeDto dto) {
        return dtoConverter.convertServiceType(save(modelConverter.convertServiceType(dto)));
    }

    @Override
    public DcServiceTypeDto updateDto(DcServiceTypeDto dto, Long id) {
        DcServiceType serviceType = update(modelConverter.convertServiceType(dto), id);
        if (serviceType == null) {
            return null;
        }
        return dtoConverter.convertServiceType(serviceType);
    }

    @Override
    public DcServiceTypeDto findOneDto(Long id) {
        DcServiceType serviceType = findOne(id);
        if (serviceType == null) {
            return null;
        }
        return dtoConverter.convertServiceType(serviceType);
    }

    @Override
    public List<DcServiceTypeDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertServiceType).collect(Collectors.toList());
    }

    @Override
    public Page<DcServiceTypeDto> findAllDto(Pageable pageable) {
        Page<DcServiceTypeDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertServiceType);
        return paginatedDto;
    }

    @Override
    public Page<DcServiceTypeDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcServiceTypeDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcServiceTypeDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertServiceType);
        return paginatedDto;
    }

}
