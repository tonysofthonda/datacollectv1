package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcPosition;
import com.honda.hdm.datacollect.model.entity.dto.DcPositionDto;
import com.honda.hdm.datacollect.repository.comm.DcPositionRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcPositionService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcPositionService extends RecordStatusableService<DcPosition, Long> implements IDcPositionService {

    @Autowired
    DcPositionRepository repository;

    @Lazy
    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    public DcPosition findOneByJobId(String jobId){
        return repository.findOneByJobId(jobId);
    }

    @Override
    public List<DcPosition> findByRoleId(Long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public Page<DcPosition> findByRoleId(Long roleId, Pageable pageable) {
        return repository.findAllByRoleId(roleId, pageable);
    }

    @Override
    public List<DcPosition> findAllByTermWithRoleId(String term, Long roleId) {
        return repository.findAllByTermWithRoleId(term, roleId);
    }

    @Override
    public Page<DcPosition> findAllByTermWithRoleId(String term, Long roleId, Pageable pageable) {
        return repository.findAllByTermWithRoleId(term, roleId, pageable);
    }

    @Override
    public Page<DcPosition> findAllByTerm(String term, Pageable pageable) {
        return repository.findAllByTerm(term, pageable);
    }

    @Override
    public DcPosition update(DcPosition position, Long id) {
        DcPosition currentPosition = findOne(id);
        if(currentPosition == null){
            return null;
        }
        currentPosition.setJobId(position.getJobId());
        return save(currentPosition);
    }

    @Override
    public List<DcPositionDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertPosition).collect(Collectors.toList());
    }

    @Override
    public DcPositionDto findOneByJobIdDto(String jobId) {
        DcPosition position = findOneByJobId(jobId);
        if(position == null){
            return null;
        }
        return dtoConverter.convertPosition(position);
    }

    @Override
    public Page<DcPositionDto> findByRoleIdDto(Long roleId, Pageable pageable) {
        return findByRoleId(roleId, pageable).map(dtoConverter::convertPosition);
    }

    @Override
    public Page<DcPositionDto> findAllByTermWithRoleIdDto(String term, Long roleId, Pageable pageable) {
        return findAllByTermWithRoleId(term, roleId, pageable).map(dtoConverter::convertPosition);
    }

    @Override
    public DcPositionDto saveDto(DcPositionDto dto) {
        return dtoConverter.convertPosition(save(modelConverter.convertPosition(dto)));
    }

    @Override
    public DcPositionDto updateDto(DcPositionDto dto, Long id) {
        DcPosition currentPosition = update(modelConverter.convertPosition(dto), id);
        if(currentPosition == null) {
            return null;
        }
        return dtoConverter.convertPosition(currentPosition);
    }

    @Override
    public DcPositionDto findOneDto(Long id) {
        DcPosition position = findOne(id);
        if(position == null){
            return null;
        }
        return dtoConverter.convertPosition(position);
    }

    @Override
    public Page<DcPositionDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertPosition);
    }

    @Override
    public Page<DcPositionDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcPositionDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertPosition);
    }
}
