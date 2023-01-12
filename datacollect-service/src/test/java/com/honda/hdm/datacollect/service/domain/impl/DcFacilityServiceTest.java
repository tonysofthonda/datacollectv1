package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.repository.DcFacilityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class DcFacilityServiceTest {
 /*   @InjectMocks
    DcFacilityService service;

    @Mock
    DcFacilityRepository repository;

    @Autowired
    Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_facilityIdNotExists_throwException() {
        DcFacility facility = spy(new DcFacility());
        facility.setId(faker.random().nextLong());
        assertNotNull(facility);

        when(repository.getOne(facility.getId())).thenReturn(facility);
        when(service.getOne(facility.getId())).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            service.getOne(facility.getId());
        });
        verify(repository, times(1)).getOne(facility.getId());
    }

    @Test
    void when_facilityExists_update() {
        DcFacility facility = spy(new DcFacility());
        facility.setId(faker.random().nextLong());
        assertNotNull(facility);
        Optional<DcFacility> dc = Optional.of(facility);
        when(repository.findById(facility.getId())).thenReturn(dc);
        when(service.update(facility, facility.getId())).thenReturn(facility);

        assertNotNull(service.update(facility, facility.getId()));

        verify(repository, times(1)).save(facility);
    }*/
}