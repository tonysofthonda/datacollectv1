package com.honda.hdm.datacollect.service.domain.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.dto.DcContactDto;
import com.honda.hdm.datacollect.repository.DcTerchiefRepository;

class DcTerchiefServiceTest {
/*
    @InjectMocks
    DcTerchiefService dcTerchiefService;

    @Mock
    DcTerchiefRepository terchiefRepository;

    @Mock
    IDcContactService contactService;

    @Mock
    DtoConverter dtoConverter;

    @Mock
    ModelConverter modelConverter;

    @Autowired
    private Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_terchiefIdExist_getContactList() {
        DcTerchief dcTerchief = Mockito.spy(new DcTerchief());
        dcTerchief.setId(faker.random().nextLong());

        Assertions.assertNotNull(dcTerchief);
        Optional<DcTerchief> dc = Optional.of(dcTerchief);
        when(terchiefRepository.findById(dcTerchief.getId())).thenReturn(dc);
        List<DcContact> contacts = dcTerchiefService.getEnabledContacts(dcTerchief.getId());

        Assertions.assertNotNull(contacts);

    }

    @Test
    void when_terchiefIdNotExist_throwException() {

        DcTerchief dcTerchief = Mockito.spy(new DcTerchief());

        Assertions.assertNotNull(dcTerchief);
        Optional<DcTerchief> dc = Optional.of(dcTerchief);
        when(terchiefRepository.findById(dcTerchief.getId())).thenReturn(dc);
        when(dcTerchiefService.getEnabledContacts(dcTerchief.getId())).thenThrow(NullPointerException.class);
    }

    @Test
    void when_terchiefExist_updateOrSaveRecord() {

        DcTerchief dcTerchief = Mockito.spy(new DcTerchief());
        dcTerchief.setId(faker.random().nextLong());
        dcTerchief.setFirstName(faker.regexify("####"));
        dcTerchief.setLastName(faker.regexify("####"));
        dcTerchief.setMotherLast(faker.regexify("####"));
        dcTerchief.setNotes(faker.expression("####"));
        Assertions.assertNotNull(dcTerchief);

        Optional<DcTerchief> dc = Optional.of(dcTerchief);
        when(terchiefRepository.findById(dcTerchief.getId())).thenReturn(dc);

        dcTerchiefService.update(dcTerchief, dcTerchief.getId());

        verify(terchiefRepository, times(1)).save(dcTerchief);
    }

    @Test
    void when_terchiefExist_saveContactRecord() {
        DcContact dcContact = new DcContact();

        dcContact.setId(faker.random().nextLong());
        dcContact.setFirstName(faker.regexify("####"));
        dcContact.setLastName(faker.regexify("####"));
        dcContact.setMotherLastName(faker.regexify("####"));
        dcContact.setPhoneNumber(faker.expression("####"));
        dcContact.setEmail(faker.expression("####"));
        dcContact.setNotes(faker.expression("####"));

        DcTerchief dcTerchief = Mockito.spy(new DcTerchief());
        dcTerchief.setId(faker.random().nextLong());
        List<DcContact> contacts = new ArrayList<>();
        contacts.add(dcContact);
        dcTerchief.setDcContactList(contacts);

        DcContactDto contactDto = new DcContactDto();

        when(dtoConverter.convertContact(dcContact)).thenReturn(contactDto);
        when(modelConverter.convertContact(contactDto)).thenReturn(dcContact);
        Optional<DcTerchief> dc = Optional.of(dcTerchief);
        when(terchiefRepository.findById(dcTerchief.getId())).thenReturn(dc);
        when(terchiefRepository.save(dcTerchief)).thenReturn(dcTerchief);
        when(contactService.save(dcContact)).thenReturn(dcContact);

        DcContactDto newContactDto = dcTerchiefService.saveContact(dcTerchief.getId(),dtoConverter.convertContact(dcContact));

        Assertions.assertNotNull(newContactDto);

    }*/
}
