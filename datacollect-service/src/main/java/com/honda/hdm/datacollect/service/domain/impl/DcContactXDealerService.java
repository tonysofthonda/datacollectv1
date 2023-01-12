package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.repository.DcContactXDealerRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DcContactXDealerService extends BaseDomainService<DcContactXDealer, Long> implements IDcContactXDealerService {

    @Autowired
    DcContactXDealerRepository repository;

    @Autowired
    IDcDealerService dealerService;

    @Autowired
    IDcContactService contactService;

    @Autowired
    IDcPositionService positionService;

    @Autowired
    IDcSystemServiceService systemServiceService;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public Page<DcContactXDealerDto> findAllByTermDto(Long dealerId, String term, Pageable pageable) {
        return repository.findAllByDealerIdTerm(dealerId, "%" + term + "%", pageable).map(dtoConverter::convertContactXDealer);
    }

    @Override
    public Page<DcContactXDealerDto> findAllDto(Long dealerId, Pageable pageable) {
        return repository.findAllByDealerId(dealerId,pageable).map(dtoConverter::convertContactXDealer);
    }

    @Override
    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public DcContactXDealerDto saveDto(DcContactXDealerDto dto) {
        DcDealerDto dealer = dealerService.findOneDto(dto.getDealer().getId());
        DcPositionDto position = positionService.findOneDto(dto.getPosition().getId());
        Boolean anyNotificationNotExists = dto.getNotifications().stream().anyMatch(systemService->
            systemServiceService.findOneDto(systemService.getId()) == null
        );
        if(dealer == null || position == null || anyNotificationNotExists){
            return null;
        }
        return dtoConverter.convertContactXDealer(save(modelConverter.convertContactXDealer(dto)));
    }

    @Override
    public DcContactXDealerDto updateDto(DcContactXDealerDto dto, Long id) {
        DcContactXDealerDto currentContactDealer = findOneDto(id);
        if(currentContactDealer == null){
            return null;
        }
        currentContactDealer.setNotifications(new ArrayList<>());
        saveDto(currentContactDealer);

        currentContactDealer.setContact(dto.getContact());
        currentContactDealer.setPosition(dto.getPosition());
        currentContactDealer.setNotifications(dto.getNotifications());
        return saveDto(currentContactDealer);
    }

    @Override
    public DcContactXDealerDto findOneDto(Long id) {
        DcContactXDealer contactXDealer = findOne(id);
        if(contactXDealer == null){
            return null;
        }
        return dtoConverter.convertContactXDealer(contactXDealer);
    }

    @Override
    public Page<DcContactXDealerDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertContactXDealer);
    }

    @Override
    public Page<DcContactXDealerDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcContactXDealerDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }
}
