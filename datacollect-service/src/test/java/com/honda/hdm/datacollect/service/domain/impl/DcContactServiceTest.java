package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcContact;
import com.honda.hdm.datacollect.repository.comm.DcContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DcContactServiceTest {
/*
    @InjectMocks
    DcContactService dcContactService;

    @Mock
    DcContactRepository repository;

    @Autowired
    private Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_contactIdExists_update() {
        DcContact dcContact = Mockito.spy(new DcContact());
        dcContact.setId(faker.random().nextLong());
        dcContact.setFirstName(faker.regexify("####"));
        dcContact.setLastName(faker.regexify("####"));
        dcContact.setMotherLastName(faker.regexify("####"));
        dcContact.setEmail(faker.regexify("####"));
        dcContact.setPhoneNumber(faker.regexify("####"));
        dcContact.setNotes(faker.expression("####"));
        Assertions.assertNotNull(dcContact);
        Optional<DcContact> dc = Optional.empty();
        dc.of(dcContact);
        when(repository.findById(dcContact.getId())).thenReturn(dc);

        dcContactService.update(dcContact, dcContact.getId());

        verify(repository, times(1)).save(dcContact);
    }
*/
}