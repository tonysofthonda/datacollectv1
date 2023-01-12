/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.repository.DcDealerRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcContactService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author VJC80439
 */
@Service
public class DcDealerService extends RecordStatusableService<DcDealer, Long> implements IDcDealerService {

    private static final Logger LOGGER = LogManager.getLogger(DcDealerService.class);

    @Autowired
    DcDealerRepository repository;

    @Autowired
    IDcContactService contactService;

    @Autowired
    private DcDealerGroupService dcDealerGroupService;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcDealer findOneByDealerNumber(String dealerNumber) {
        if (StringUtils.isEmpty(dealerNumber))
            throw new IllegalArgumentException("Dealer Number is required for querying database");
        return repository.findOneByDealerNumberIgnoreCase(dealerNumber);
    }

    @Override
    public DcDealer findOneByName(String name) {
        if (StringUtils.isEmpty(name))
            throw new IllegalArgumentException("Dealer Name is required for querying database");
        return repository.findOneByNameIgnoreCase(name);
    }

    @Override
    public Long countByDealerGroup(Long dealerGroupId) {
        if (dealerGroupId <= 0) {
            throw new IllegalArgumentException("Dealer Group is required for querying database");
        }
        DcDealerGroup dealerGroup = dcDealerGroupService.findOne(new BigDecimal(dealerGroupId));
        if (dealerGroup == null) {
            throw new RecordNotFounException("Dealer Group with ID " + dealerGroupId + " not found");
        }
        return repository.countByDcDealerGroup(dealerGroup);
    }

    @Override
    @Transactional
    public List<DcContact> getEnabledContacts(Long dealerId) {
        DcDealer dealer = findOne(dealerId);
        if (dealer == null) {
            throw new RecordNotFounException("Dealer with Id(" + dealerId + ") does not exist on DB");
        }
        return getEnabledContacts(dealer);
    }

    @Override
    @Transactional
    public List<DcContact> getEnabledContacts(String dealerNumber) {
        DcDealer dealer = findOneByDealerNumber(dealerNumber);
        if (dealer == null) {
            throw new RecordNotFounException("Dealer with Number(" + dealerNumber + ") does not exist on DB");
        }
        return getEnabledContacts(dealer);
    }

    @Override
    public List<DcContact> getEnabledContacts(DcDealer dealer){
        List<DcContact> originalList = dealer.getDcContactList();
        List<DcContact> contactList = new ArrayList<>();
        if(originalList == null || originalList.isEmpty()){
            return contactList;            
        }
        originalList.stream().filter((contact) -> (contact.getDcRecordStatusId() == DcRecordStatusEnum.ENABLED)).forEachOrdered((contact) -> {
            contactList.add(contact);
         });
        return contactList;
    }


    @Override
    public Page<DcContact> getEnabledContacts(String dealerNumber, Pageable pageable) {
        List<DcContact> contacts = getEnabledContacts(dealerNumber);
        return new PageImpl<>(contacts, pageable, contacts.size());
    }

    @Override
    public DcDealer update(DcDealer dealer, Long id) {
        DcDealer currentDealer = findOne(id);
        if (currentDealer == null) {
            return null;
        }
        currentDealer.setDealerNumber(dealer.getDealerNumber());
        currentDealer.setName(dealer.getName());
        currentDealer.setBusinessName(dealer.getBusinessName());
        currentDealer.setRfc(dealer.getRfc());
        currentDealer.setAddrPostcode(dealer.getAddrPostcode());
        currentDealer.setDcCityId(dealer.getDcCityId());
        currentDealer.setAddrStreet(dealer.getAddrStreet());
        currentDealer.setAddrNeighborhood(dealer.getAddrNeighborhood());
        currentDealer.setDcDealerGroup(dealer.getDcDealerGroup());
        currentDealer.setDcTerchief(dealer.getDcTerchief());
        currentDealer.setDcWorkshopId(dealer.getDcWorkshopId());
        return save(currentDealer);
    }

    @Override
    public DcDealerDto saveDto(DcDealerDto dto) {
        return dtoConverter.convertDealer(save(modelConverter.convertDealer(dto)));
    }

    @Override
    public DcDealerDto updateDto(DcDealerDto dto, Long id) {
        DcDealer dealerUpdated = update(modelConverter.convertDealer(dto), id);
        if (dealerUpdated == null) {
            return null;
        }
        return dtoConverter.convertDealer(dealerUpdated);
    }

    @Override
    public DcDealerDto findOneDto(Long id) {
        DcDealer dealer = findOne(id);
        if (dealer == null) {
            return null;
        }
        return dtoConverter.convertDealer(dealer);
    }

    @Override
    public Page<DcDealerDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertDealer);
    }

    @Override
    public Page<DcDealerDto> findAllByTermDto(String term, Pageable pageable) {
        return repository.findAllByTerm(term, pageable).map(dtoConverter::convertDealer);
    }

    @Override
    public Page<DcDealerDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertDealer);
    }

    @Override
    public DcDealerDto findOneByDealerNumberDto(String dealerNumber) {
        DcDealer dealer = findOneByDealerNumber(dealerNumber);
        if (dealer == null) {
            return null;
        }
        return dtoConverter.convertDealer(dealer);
    }

    @Override
    public DcDealerDto findOneByNameDto(String name) {
        DcDealer dealer = findOneByName(name);
        if (dealer == null) {
            return null;
        }
        return dtoConverter.convertDealer(dealer);
    }

    @Override
    public DcDealerDto changeStatus(Boolean status, Long id) {
        DcDealer currentDealer = findOne(id);
        if (currentDealer == null) {
            return null;
        }
        currentDealer.setDcRecordStatusId(status ? DcRecordStatusEnum.ENABLED : DcRecordStatusEnum.DISABLED);
        return dtoConverter.convertDealer(save(currentDealer));
    }
}
