package com.honda.hdm.datacollect.service.domain.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.repository.DcDealerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DcDealerServiceTest {
/*
    @InjectMocks
    DcDealerService dealerService;

    @Mock
    DcDealerRepository repository;

    @Autowired
    private Faker faker = new Faker();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void when_dealerExist_update() {
        DcDealer dealer = new DcDealer();
        dealer.setId(faker.random().nextLong());
        Optional<DcDealer> dc = Optional.of(dealer);
        when(repository.findById(dealer.getId())).thenReturn(dc);
        when(dealerService.update(dealer, dealer.getId())).thenReturn(dealer);

        DcDealer dealerUpdated = dealerService.update(dealer, dealer.getId());
        assertNotNull(dealerUpdated);

        verify(repository,times(1)).save(dealer);
    }*/
}