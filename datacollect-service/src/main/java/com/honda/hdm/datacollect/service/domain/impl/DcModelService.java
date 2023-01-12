/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.dto.DcModelDto;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.honda.hdm.datacollect.model.entity.DcModel;
import com.honda.hdm.datacollect.repository.DcModelRepository;
import com.honda.hdm.datacollect.service.domain.IDcModelService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author VJC80439
 */
@Service
public class DcModelService extends RecordStatusableService<DcModel, Long> implements IDcModelService {

    @Autowired
    DcModelRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcModel findOneByCode(String dcModelCode) throws Exception {
        if (StringUtils.isEmpty(dcModelCode)) {
            throw new Exception("Code is mandatory for searching DcModel into database");
        }
        return repository.findOneByCodeIgnoreCase(dcModelCode);
    }

    @Override
    public DcModel findOneByCodeAndYear(String code, String year) {
        return repository.findFirstByCodeAndYear(code, year);
    }

    @Override
    public DcModel update(DcModel obj, Long id) {
        Optional<DcModel> currentModel = repository.findById(id);
        if (!currentModel.isPresent()) {
            return null;
        }
        currentModel.get().setDcSystemServices(new ArrayList<>());
        repository.save(currentModel.get());

        currentModel.get().setCode(obj.getCode());
        currentModel.get().setYear(obj.getYear());
        currentModel.get().setName(obj.getName());
        currentModel.get().setDescription(obj.getDescription());
        currentModel.get().setAccountNumber(obj.getAccountNumber());
        currentModel.get().setDcSystemServices(obj.getDcSystemServices());
        return repository.save(currentModel.get());
    }

    @Override
    public DcModelDto findOneByCodeDto(String dcModelCode) {
        try {
            return dtoConverter.convertModel(findOneByCode(dcModelCode));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DcModelDto findOneByCodeAndYearDto(String code, Integer year) {
        DcModel model = findOneByCodeAndYear(code, String.valueOf(year));
        if (model == null) {
            return null;
        }
        return dtoConverter.convertModel(model);
    }

    @Override
    public DcModelDto saveDto(DcModelDto dto) {
        return dtoConverter.convertModel(save(modelConverter.convertModel(dto)));
    }

    @Override
    public DcModelDto updateDto(DcModelDto dto, Long id) {
        DcModel model = update(modelConverter.convertModel(dto), id);
        if (model == null) {
            return null;
        }
        return dtoConverter.convertModel(model);
    }

    @Override
    public DcModelDto findOneDto(Long id) {
        DcModel model = findOne(id);
        if (model == null) {
            return null;
        }
        return dtoConverter.convertModel(model);
    }

    @Override
    public Page<DcModelDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertModel);
    }

    @Override
    public Page<DcModelDto> findAllByTermDto(String term, Pageable pageable) {
        return repository.findAllByTerm(term, pageable).map(dtoConverter::convertModel);
    }

    @Override
    public Page<DcModelDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertModel);
    }

    @Override
    public DcModelDto changeStatus(Boolean status, Long id) {
        DcModel model = findOne(id);
        if (model == null) {
            return null;
        }
        model.setDcRecordStatusId(status ? DcRecordStatusEnum.ENABLED: DcRecordStatusEnum.DISABLED);
        return dtoConverter.convertModel(save(model));
    }
}
