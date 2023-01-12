package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcSystemService;
import com.honda.hdm.datacollect.model.entity.dto.DcSystemServiceDto;
import com.honda.hdm.datacollect.repository.comm.DcSystemServiceRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcSystemServiceService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcSystemServiceService extends RecordStatusableService<DcSystemService, Long> implements IDcSystemServiceService {

    @Autowired
    DcSystemServiceRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcSystemService update(DcSystemService systemService, Long id) {
        DcSystemService currentSystemService = findOne(id);
        if (systemService == null) {
            return null;
        }
        currentSystemService.setName(systemService.getName());
        return repository.save(currentSystemService);
    }

    @Override
    public DcSystemServiceDto saveDto(DcSystemServiceDto dto) {
        return dtoConverter.convertSystemService(save(modelConverter.convertSystemService(dto)));
    }

    @Override
    public DcSystemServiceDto updateDto(DcSystemServiceDto dto, Long id) {
        DcSystemService systemService = update(modelConverter.convertSystemService(dto), id);
        if (systemService == null) {
            return null;
        }
        return dtoConverter.convertSystemService(systemService);
    }

    @Override
    public DcSystemServiceDto findOneDto(Long id) {
        DcSystemService systemService = findOne(id);
        if (systemService == null) {
            return null;
        }
        return dtoConverter.convertSystemService(systemService);
    }

    @Override
    public List<DcSystemServiceDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertSystemService).collect(Collectors.toList());
    }

    @Override
    public Page<DcSystemServiceDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertSystemService);
    }

    @Override
    public Page<DcSystemServiceDto> findAllByTermDto(String term, Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertSystemService);
    }

    @Override
    public Page<DcSystemServiceDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertSystemService);
    }


}
