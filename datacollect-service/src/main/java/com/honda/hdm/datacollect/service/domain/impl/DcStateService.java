/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcState;
import com.honda.hdm.datacollect.model.entity.dto.DcStateDto;
import com.honda.hdm.datacollect.repository.DcStateRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcStateService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author VJC80439
 */
@Service
public class DcStateService extends RecordStatusableService<DcState, BigDecimal> implements IDcStateService {

    private static final Logger LOGGER = LogManager.getLogger(DcStateService.class);

    @Autowired
    DcStateRepository repository;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public BigInteger findOneByName(String name) throws Exception {
        if (StringUtils.isEmpty(name))
            throw new Exception("Name is mandatory for searching a State");

        BigDecimal idState = repository.findOneByNameIgnoreCase(name);
        BigInteger i_idState = idState.toBigInteger();
        return i_idState;
    }

    @Override
    public DcState update(DcState state, BigDecimal id) {
        DcState currentState = findOne(id);
        if (currentState == null) {
            return null;
        }
        currentState.setName(state.getName());
        return save(currentState);
    }

    @Override
    public List<DcStateDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertState).collect(Collectors.toList());
    }

    @Override
    public DcStateDto saveDto(DcStateDto dto) {
        return dtoConverter.convertState(save(modelConverter.convertState(dto)));
    }

    @Override
    public DcStateDto updateDto(DcStateDto dto, BigDecimal id) {
        DcState currentState = update(modelConverter.convertState(dto), id);
        if (currentState == null) {
            return null;
        }
        return dtoConverter.convertState(currentState);
    }

    @Override
    public DcStateDto findOneDto(BigDecimal id) {
        DcState state = findOne(id);
        if (state == null) {
            return null;
        }
        return dtoConverter.convertState(state);
    }

    @Override
    public Page<DcStateDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertState);
    }

    @Override
    public Page<DcStateDto> findAllByTermDto(String term, Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertState);
    }

    @Override
    public Page<DcStateDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertState);
    }
}
