package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.model.entity.dto.*;
import com.honda.hdm.datacollect.repository.DcFacilityXDealerRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcFacilityService;
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
import static org.mockito.Mockito.times;

class DcFacilityXDealerServiceTest {
   /* 
    @InjectMocks
    DcFacilityXDealerService facilityXDealerService;

    @Mock
    DcFacilityXDealerRepository repository;

    @Mock
    IDcDealerService dealerService;

    @Mock
    IDcFacilityService facilityService;

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
        DcFacilityXDealer facilityXDealer = new DcFacilityXDealer();
        DcFacilityXDealerPK id = new DcFacilityXDealerPK();
        id.setDcDealerId(faker.random().nextLong());
        id.setDcFacilityId(faker.random().nextLong());
        facilityXDealer.setDcFacilityXDealerPK(id);
        facilityXDealer.setQuantity(faker.random().nextInt(1, 10));

        when(repository.save(facilityXDealer)).thenReturn(facilityXDealer);
        Optional<DcFacilityXDealer> dc = Optional.of(facilityXDealer);
        when(repository.findById(facilityXDealer.getDcFacilityXDealerPK())).thenReturn(dc);

        assertNotNull(facilityXDealerService.update(facilityXDealer, facilityXDealer.getDcFacilityXDealerPK()));

        verify(repository,times(1)).save(facilityXDealer);

    }

    @Test
    void when_contactDealerIdNotExists_return_empty_page() {
        DcFacilityXDealer facilityXDealer = new DcFacilityXDealer();
        DcFacilityXDealerPK id = new DcFacilityXDealerPK();
        id.setDcDealerId(faker.random().nextLong());
        id.setDcFacilityId(faker.random().nextLong());
        facilityXDealer.setDcFacilityXDealerPK(id);
        PageRequest pageRequest = PageRequest.of(1, 10);
        when(facilityXDealerService.findAllByDealerId(facilityXDealer.getDcFacilityXDealerPK().getDcDealerId(), pageRequest)).thenReturn(new PageImpl<>(new ArrayList<>()));

        Page pageContactDealers = facilityXDealerService.findAllByDealerId(facilityXDealer.getDcFacilityXDealerPK().getDcDealerId(), pageRequest);

        assertEquals(pageContactDealers.getTotalElements(), 0);
    }
*/
}