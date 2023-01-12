package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcDealerGroup;
import com.honda.hdm.datacollect.repository.DcDealerGroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DcDealerGroupServiceTest {
/*
    @InjectMocks
    DcDealerGroupService service;

    @Mock
    DcDealerGroupRepository repository;

    @Autowired
    private Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void when_dealerGroupIdNotExits_throwException() {
        DcDealerGroup dealerGroup = Mockito.spy(new DcDealerGroup());
        dealerGroup.setId(new BigDecimal(faker.random().nextLong()));
        Assertions.assertNotNull(dealerGroup);
        when(repository.getOne(dealerGroup.getId())).thenReturn(dealerGroup);
        when(service.getOne(dealerGroup.getId())).thenThrow(NullPointerException.class);

        Assertions.assertThrows(NullPointerException.class, () -> {
            service.getOne(dealerGroup.getId());
        });
    }

    @Test
    void when_dealerGroupExist_updateOrSaveRecord() {

        DcDealerGroup dealerGroup = Mockito.spy(new DcDealerGroup());
        dealerGroup.setId(new BigDecimal(faker.random().nextLong()));
        Assertions.assertNotNull(dealerGroup);
        Optional<DcDealerGroup> dc = Optional.of(dealerGroup);
        when(repository.findById(dealerGroup.getId())).thenReturn(dc);

        service.update(dealerGroup, dealerGroup.getId());

        verify(repository, times(1)).save(dealerGroup);
    }*/
}