package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.*;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.repository.DcContactXDealerRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcPositionService;
import com.honda.hdm.datacollect.service.domain.IDcSystemServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DcContactXDealerServiceTest {
/*
    @InjectMocks
    DcContactXDealerService contactXDealerService;

    @Mock
    DcContactXDealerRepository repository;

    @Mock
    IDcDealerService dealerService;

    @Mock
    IDcPositionService positionService;

    @Mock
    IDcSystemServiceService systemServiceService;

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
    void when_contactDealerIdExists_update() {
        DcContactXDealerDto dcContactDealer = Mockito.spy(new DcContactXDealerDto());
        dcContactDealer.setId(faker.random().nextLong());
        DcDealerDto dealer = new DcDealerDto();
        dealer.setId(faker.random().nextLong());
        dcContactDealer.setDealer(dealer);
        DcContactDto dcContact = new DcContactDto();
        dcContact.setId(faker.random().nextLong());
        dcContactDealer.setContact(dcContact);
        DcPositionDto position = new DcPositionDto();
        position.setId(faker.random().nextLong());
        dcContactDealer.setPosition(position);
        dcContactDealer.setNotifications(new ArrayList<>());
        DcSystemServiceDto systemService = new DcSystemServiceDto();
        systemService.setId(faker.random().nextLong());
        dcContactDealer.getNotifications().add(systemService);


        DcContactXDealer contactXDealer = new DcContactXDealer();

        when(dtoConverter.convertContactXDealer(Mockito.any())).thenReturn(dcContactDealer);
        when(modelConverter.convertContactXDealer(Mockito.any())).thenReturn(contactXDealer);

        when(contactXDealerService.saveDto(dcContactDealer)).thenReturn(dcContactDealer);
        Optional<DcContactXDealer> dc = Optional.empty();
        when(repository.findById(dcContactDealer.getId())).thenReturn(dc);
        when(dealerService.findOneDto(dcContactDealer.getDealer().getId())).thenReturn(dealer);
        when(positionService.findOneDto(position.getId())).thenReturn(position);
        when(systemServiceService.findOneDto(Mockito.any())).thenReturn(systemService);
        when(contactXDealerService.updateDto(dcContactDealer, dcContactDealer.getId())).thenReturn(dcContactDealer);

        assertNotNull(contactXDealerService.updateDto(dcContactDealer, dcContactDealer.getId()));

        verify(repository,atLeastOnce()).save(contactXDealer);

    }

    @Test
    void when_contactDealerIdNotExists_return_empty_page() {
        DcContactXDealer dcContactDealer = Mockito.spy(new DcContactXDealer());
        dcContactDealer.setId(faker.random().nextLong());
        PageRequest pageRequest = PageRequest.of(1, 10);
        when(repository.findAllByDealerId(dcContactDealer.getId(), pageRequest)).thenReturn(new PageImpl<>(new ArrayList<>()));

        Page pageContactDealers = contactXDealerService.findAllDto(dcContactDealer.getId(), pageRequest);

        verify(repository, times(1)).findAllByDealerId(dcContactDealer.getId(), pageRequest);

        assertEquals(pageContactDealers.getTotalElements(), 0);
    }

*/
}