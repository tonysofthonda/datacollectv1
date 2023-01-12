/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.repository.comm.DcContactRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcContactService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author VJC80439
 */
@Service
public class DcContactService extends RecordStatusableService<DcContact, Long> implements IDcContactService {

	@Lazy
    @Autowired
    DcContactRepository repository;
    
    @Lazy
    @Autowired
    DtoConverter dtoConverter;
    
    @Lazy
    @Autowired
    ModelConverter modelConverter;

    @Override
    public DcContact update(DcContact contact, Long id) {
        Optional<DcContact> currentContact = repository.findById(id);
        if (!currentContact.isPresent()) {
            return null;
        }
        currentContact.get().setPhoneNumber(contact.getPhoneNumber());
        currentContact.get().setFirstName(contact.getFirstName());
        currentContact.get().setLastName(contact.getLastName());
        currentContact.get().setMotherLastName(contact.getMotherLastName());
        currentContact.get().setEmail(contact.getEmail());
        currentContact.get().setNotes(contact.getNotes());
        return repository.save(currentContact.get());
    }

    @Override
    public DcContactDto saveDto(DcContactDto dto) {
        DcContact contact = modelConverter.convertContact(dto);
        DcContact newContact = save(contact);
        return dtoConverter.convertContact(newContact);
    }

    @Override
    public DcContactDto updateDto(DcContactDto dto, Long id) {
        DcContact contact = update(modelConverter.convertContact(dto), id);
        if (contact == null) {
            return null;
        }
        return dtoConverter.convertContact(contact);
    }

    @Override
    public DcContactDto findOneDto(Long id) {
        DcContact contact = findOne(id);
        if (contact == null) {
            return null;
        }
        return dtoConverter.convertContact(contact);
    }

    @Override
    public Page<DcContactDto> findAllDto(Pageable pageable) {
        Page<DcContactDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertContact);
        return paginatedDto;
    }

    @Override
    public Page<DcContactDto> findAllByTermDto(String term, Pageable pageable) {
        Page<DcContactDto> paginatedDto = repository.findAllByTerm(term, pageable).map(dtoConverter::convertContact);
        return paginatedDto;
    }

    @Override
    public Page<DcContactDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcContactDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertContact);
        return paginatedDto;
    }

}
