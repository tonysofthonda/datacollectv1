/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityDto;
import com.honda.hdm.datacollect.repository.DcFacilityRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcFacilityService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author VJC80439
 */
@Service
public class DcFacilityService extends RecordStatusableService<DcFacility, Long> implements IDcFacilityService {

    @Autowired
    DcFacilityRepository repository;

    @Lazy
    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    public DcFacility findIfExistByConcept(String concept) {
        return repository.findIfExistByConcept(concept);
    }

    @Override
    public DcFacility update(DcFacility obj, Long id) {
        Optional<DcFacility> currentFacility = repository.findById(id);
        if (!currentFacility.isPresent()) {
            return null;
        }
        currentFacility.get().setConcept(obj.getConcept());
        currentFacility.get().setDescription(obj.getDescription());
        return repository.save(currentFacility.get());
    }

    @Override
    public DcFacilityDto saveDto(DcFacilityDto dto) {
        DcFacility facility = modelConverter.convertFacility(dto);
        DcFacility newFacility = save(facility);
        return dtoConverter.convertFacility(newFacility);
    }

    @Override
    public DcFacilityDto updateDto(DcFacilityDto dto, Long id) {
        DcFacility facility = update(modelConverter.convertFacility(dto), id);
        if (facility == null) {
            return null;
        }
        return dtoConverter.convertFacility(facility);
    }

    @Override
    public DcFacilityDto findOneDto(Long id) {
        DcFacility facility = findOne(id);
        if (facility == null) {
            return null;
        }
        return dtoConverter.convertFacility(facility);
    }

    @Override
    public List<DcFacilityDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertFacility).collect(Collectors.toList());
    }

    @Override
    public Page<DcFacilityDto> findAllDto(Pageable pageable) {
        Page<DcFacilityDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertFacility);
        return paginatedDto;
    }

    @Override
    public Page<DcFacilityDto> findAllByTermDto(String term, Pageable pageable) {
        Page<DcFacilityDto> paginatedDto = repository.findAllByTerm(term, pageable).map(dtoConverter::convertFacility);
        return paginatedDto;
    }

    @Override
    public Page<DcFacilityDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcFacilityDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertFacility);
        return paginatedDto;
    }
}
