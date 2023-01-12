package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcViewDto;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcViewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.honda.hdm.datacollect.model.entity.DcView;
import com.honda.hdm.datacollect.repository.DcViewRepository;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DcViewService extends BaseDomainService<DcView, Long> implements IDcViewService {
    
    private static final Logger LOGGER = LogManager.getLogger(DcViewService.class);
    
    @Lazy
    @Autowired
    DcViewRepository repository;

    @Lazy
    @Autowired
    DtoConverter dtoConverter;

    @Lazy
    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcViewDto saveDto(DcViewDto dto) {
        return dtoConverter.convertView(modelConverter.convertView(dto));
    }

    @Override
    public DcViewDto updateDto(DcViewDto dto, Long id) {
        DcView view = findOne(id);
        if(view == null){
            return null;
        }
        view.setName(dto.getName());
        view.setFriendlyName(dto.getFriendlyName());
        view.setRoute(dto.getRoute());
        view.setOrder(dto.getOrder());
        return saveDto(dtoConverter.convertView(view));
    }

    @Override
    public DcViewDto findOneDto(Long id) {
        DcView view = findOne(id);
        if(view == null){
            return null;
        }
        return dtoConverter.convertView(view);
    }

    @Override
    public List<DcViewDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertView).collect(Collectors.toList());
    }

    @Override
    public Page<DcViewDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertView);
    }

    @Override
    public Page<DcViewDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcViewDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }
}
