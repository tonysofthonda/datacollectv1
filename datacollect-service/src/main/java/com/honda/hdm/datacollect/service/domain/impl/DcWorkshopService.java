package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcWorkshop;
import com.honda.hdm.datacollect.model.entity.dto.DcWorkshopDto;
import com.honda.hdm.datacollect.repository.DcWorkshopRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcWorkshopService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcWorkshopService extends RecordStatusableService<DcWorkshop, Long> implements IDcWorkshopService {

    private static final Logger LOGGER = LogManager.getLogger(DcWorkshopService.class);

    @Autowired
    DcWorkshopRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    public DcWorkshop findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public DcWorkshop update(DcWorkshop workshop, Long id) {
        DcWorkshop currentWorkshop = findOne(id);
        if (currentWorkshop == null) {
            return null;
        }
        currentWorkshop.setName(workshop.getName());
        return save(currentWorkshop);
    }

    @Override
    public DcWorkshopDto findByNameDto(String name) {
        DcWorkshop workshop = findByName(name);
        if (workshop == null) {
            return null;
        }
        return dtoConverter.convertWorkshop(workshop);
    }

    @Override
    public DcWorkshopDto saveDto(DcWorkshopDto dto) {
        return dtoConverter.convertWorkshop(save(modelConverter.convertWorkshop(dto)));
    }

    @Override
    public DcWorkshopDto updateDto(DcWorkshopDto dto, Long id) {
        DcWorkshop workshop = update(modelConverter.convertWorkshop(dto), id);
        if (workshop == null) {
            return null;
        }
        return dtoConverter.convertWorkshop(workshop);
    }

    @Override
    public DcWorkshopDto findOneDto(Long id) {
        DcWorkshop workshop = findOne(id);
        if (workshop == null) {
            return null;
        }
        return dtoConverter.convertWorkshop(workshop);
    }

    @Override
    public Page<DcWorkshopDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertWorkshop);
    }

    @Override
    public List<DcWorkshopDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertWorkshop).collect(Collectors.toList());
    }

    @Override
    public Page<DcWorkshopDto> findAllByTermDto(String term, Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertWorkshop);
    }

    @Override
    public Page<DcWorkshopDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertWorkshop);
    }
}
