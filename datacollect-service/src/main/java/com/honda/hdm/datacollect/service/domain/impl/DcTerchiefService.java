/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.model.entity.dto.DcTerchiefDto;
import com.honda.hdm.datacollect.repository.DcTerchiefRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcContactService;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author VJC80439
 */
@Service
public class DcTerchiefService extends RecordStatusableService<DcTerchief, Long> implements IDcTerchiefService {

    private static final Logger LOGGER = LogManager.getLogger(DcTerchiefService.class);

    @Autowired
    DcTerchiefRepository repository;

    @Autowired
    IDcContactService contactService;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;
    
    @Override
    public DcTerchief update(DcTerchief terchief, Long id) {
        Optional<DcTerchief> currentTerchief = repository.findById(id);
        if (!currentTerchief.isPresent()) {
            return null;
        }
        currentTerchief.get().setFirstName(terchief.getFirstName());
        currentTerchief.get().setLastName(terchief.getLastName());
        currentTerchief.get().setMotherLast(terchief.getMotherLast());
        currentTerchief.get().setNotes(terchief.getNotes());
        return repository.save(currentTerchief.get());
    }

    @Override
    public DcTerchiefDto saveDto(DcTerchiefDto dto) {
        DcTerchief terchief = modelConverter.convertTerchief(dto);
        DcTerchief newTerchief = save(terchief);
        return dtoConverter.convertTerchief(newTerchief);
    }

    @Override
    public DcTerchiefDto updateDto(DcTerchiefDto dto, Long id) {
        DcTerchief terchief = update(modelConverter.convertTerchief(dto), id);
        if(terchief == null){
            return null;
        }
        return dtoConverter.convertTerchief(terchief);
    }

    @Override
    public List<DcTerchiefDto> findAllDto() {
        return findByRecordStatusId(DcRecordStatusEnum.ENABLED).stream().map(dtoConverter::convertTerchief).collect(Collectors.toList());
    }

    @Override
    public DcTerchiefDto findOneDto(Long id) {
        DcTerchief terchief = findOne(id);
        if (terchief == null) {
            return null;
        }
        return dtoConverter.convertTerchief(terchief);
    }

    @Override
    public Page<DcTerchiefDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Page<DcTerchiefDto> paginatedDto = findByRecordStatusId(dcRecordStatus, pageable).map(dtoConverter::convertTerchief);
        return paginatedDto;
    }

    @Override
    public Page<DcTerchiefDto> findAllDto(Pageable pageable) {
        Page<DcTerchiefDto> paginatedDto = repository.findAll(pageable).map(dtoConverter::convertTerchief);
        return paginatedDto;
    }

    @Override
    public Page<DcTerchiefDto> findAllByTermDto(String term, Pageable pageable) {
        Page<DcTerchiefDto> paginatedDto = repository.findAllByTerm(term, pageable).map(dtoConverter::convertTerchief);
        return paginatedDto;
    }

    @Override
    public Page<DcContactDto> getEnabledContactsDto(Long terchiefId, Pageable pageable) {
        Page<DcContactDto> contacts = getEnabledContacts(terchiefId, pageable).map(dtoConverter::convertContact);
        return contacts;
    }

    @Override
    public Page<DcContact> getEnabledContacts(Long terchiefId, Pageable pageable) {
        List<DcContact> contacts = getEnabledContacts(terchiefId);
        return new PageImpl<>(contacts, pageable, contacts.size());
    }

    @Override
    @Transactional
    public List<DcContact> getEnabledContacts(Long terchiefId) {
        return getContacts(terchiefId);
    }

    @Override
    public Page<DcContactDto> filterContactsByTerm(Long idTerchief, String term, Pageable pageable) {
        List<DcContactDto> contacts = getContacts(idTerchief).stream()
                .filter(DcContact.filterFileds(term))
                .map(dtoConverter::convertContact)
                .collect(Collectors.toList());
        return new PageImpl(contacts, pageable, contacts.size());
    }

    @Override
    public DcContactDto saveContact(Long idTerchief, DcContactDto contact) {
        DcTerchief terchief = findOne(idTerchief);
        DcContact newContact = contactService.save(modelConverter.convertContact(contact));
        terchief.getDcContactList().add(newContact);
        save(terchief);
        return dtoConverter.convertContact(newContact);
    }

    private List<DcContact> getContacts(Long terchiefId) {
        Optional<DcTerchief> terchief = repository.findById(terchiefId);
        if (!terchief.isPresent()) {
            throw new RecordNotFounException("Territory Chief with ID(" + terchiefId + ") not found");
        }
        List<DcContact> originalList = terchief.get().getDcContactList();
        List<DcContact> contactList = new ArrayList<>();
        if (originalList == null || originalList.isEmpty()) {
            return contactList;
        }
        originalList.stream().filter((contact) -> (contact.getDcRecordStatusId() == DcRecordStatusEnum.ENABLED)).forEachOrdered((contact) -> {
            contactList.add(contact);
        });
        return contactList;
    }

}
