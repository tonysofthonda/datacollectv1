package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcOrderType;
import com.honda.hdm.datacollect.model.entity.dto.DcOrderTypeDto;
import com.honda.hdm.datacollect.repository.DcOrderTypeRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcOrderTypeService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author VJC80439
 */
@Service
public class DcOrderTypeService extends RecordStatusableService<DcOrderType, BigDecimal> implements IDcOrderTypeService {

    @Autowired
    private DcOrderTypeRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcOrderType findOneByCode(String code) throws Exception {
        if (StringUtils.isEmpty(code)) {
            throw new Exception("Order Type Code is required for searching on database.");
        }

        return repository.findOneByCodeIgnoreCase(code);
    }

    @Override
    public List<DcOrderType> findDcOrderType() throws Exception {
        return repository.findAll();
    }

    public DcOrderType findById(BigDecimal id) {
        return repository.findById(id).get();
    }

    @Override
    public DcOrderType update(DcOrderType obj, BigDecimal id) {
        Optional<DcOrderType> currentOrderType = repository.findById(id);
        if (!currentOrderType.isPresent()) {
            return null;
        }
        currentOrderType.get().setDcServicesTypes(new ArrayList<>());
        repository.save(currentOrderType.get());

        currentOrderType.get().setCode(obj.getCode());
        currentOrderType.get().setDescription(obj.getDescription());
        currentOrderType.get().setDcServicesTypes(obj.getDcServicesTypes());
        return repository.save(currentOrderType.get());
    }

    @Override
    public DcOrderTypeDto findOneByCodeDto(String code) throws Exception {
        DcOrderType orderType = findOneByCode(code);
        if (orderType == null) {
            return null;
        }
        return dtoConverter.convertOrderType(orderType);
    }

    @Override
    public DcOrderTypeDto saveDto(DcOrderTypeDto dto) {
        return dtoConverter.convertOrderType(save(modelConverter.convertOrderType(dto)));

    }

    @Override
    public DcOrderTypeDto updateDto(DcOrderTypeDto dto, BigDecimal id) {
        DcOrderType orderType = update(modelConverter.convertOrderType(dto), id);
        if (orderType == null) {
            return null;
        }
        return dtoConverter.convertOrderType(orderType);
    }

    @Override
    public DcOrderTypeDto findOneDto(BigDecimal id) {
        DcOrderType orderType = findOne(id);
        if (orderType == null) {
            return null;
        }
        return dtoConverter.convertOrderType(orderType);
    }

    @Override
    public Page<DcOrderTypeDto> findAllDto(Pageable pageable) {
        Page<DcOrderTypeDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertOrderType);
        return paginatedDto;
    }

    @Override
    public Page<DcOrderTypeDto> findAllByTermDto(String term, Pageable pageable) {
        Page<DcOrderTypeDto> paginatedDto = repository.findAllByTerm(term, pageable).map(dtoConverter::convertOrderType);
        return paginatedDto;
    }

    @Override
    public Page<DcOrderTypeDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcOrderTypeDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertOrderType);
        return paginatedDto;
    }

}
