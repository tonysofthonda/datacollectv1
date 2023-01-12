package com.honda.hdm.datacollect.service.domain.impl;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcModel;
import com.honda.hdm.datacollect.repository.DcModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DcModelServiceTest {
/*
    @InjectMocks
    DcModelService modelService;

    @Mock
    DcModelRepository repository;

    @Autowired
    Faker faker = new Faker();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void when_codeYearExists_getModel() {
        DcModel model = Mockito.spy(new DcModel());
        model.setYear(faker.regexify("####"));
        model.setCode(faker.regexify("####"));

        Mockito.when(repository.findFirstByCodeAndYear(model.getCode(), model.getYear())).thenReturn(model);

        assertNotNull(modelService.findOneByCodeAndYear(model.getCode(), model.getYear()));
        Mockito.verify(repository,Mockito.times(1)).findFirstByCodeAndYear(model.getCode(), model.getYear());
    }*/
}