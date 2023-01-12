package com.honda.hdm.datacollect.service.domain.impl;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.honda.hdm.datacollect.service.converter.DtoConverter;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.data.domain.Pageable;

import com.github.javafaker.Faker;
import com.honda.hdm.datacollect.model.entity.DcOperationCode;
import com.honda.hdm.datacollect.model.entity.dto.DcOperationCodeDto;
import com.honda.hdm.datacollect.repository.DcOperationCodeRepository;

/**
 * @author VJC80519
 * @package com.honda.hdm.datacollect.service.domain.impl
 * @project datacollect-service
 * @date 25 nov 2021
 *
 */
class DcOperationCodeServiceTest {
/*
	@InjectMocks
	DcOperationCodeService dcOperationCodeService;
	
	@Mock
	DcOperationCodeRepository dcOperationCodeRepository;

	@Mock
	DtoConverter dtoConverter;
	
	@Autowired
	private Faker faker = new Faker();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void when_executeGetOperationCodes_getOperationCodeList() throws Exception {
		DcOperationCode operationCode = Mockito.spy(new DcOperationCode());
		List<DcOperationCode> operationCodeList = new ArrayList<>();
		operationCodeList.add(operationCode);
		Assertions.assertNotNull(operationCodeList);
		
		Page<DcOperationCode> pagedResponse = new PageImpl<DcOperationCode>(operationCodeList);
		
		Pageable pageable = PageRequest.of(faker.random().nextInt(0, 10), faker.random().nextInt(10, 20));

		when(dtoConverter.convertOperationCode(Mockito.any())).thenReturn(new DcOperationCodeDto());
		when(dcOperationCodeRepository.findAll(pageable)).thenReturn(pagedResponse);
		
		dcOperationCodeService.getOperationCodeList(pageable);
	}

	@Test
	void when_operationCodeDtoIsValid_sevedOrUpdateRecord() {
		DcOperationCode operationCode = Mockito.spy(new DcOperationCode());
		operationCode.setId(BigDecimal.ONE);
		
		Assertions.assertNotNull(operationCode);
		
		DcOperationCodeDto operationCodeDto = Mockito.spy(new DcOperationCodeDto());
		Assertions.assertNotNull(operationCodeDto);
		Optional<DcOperationCode> dc = Optional.of(operationCode);
		when(dcOperationCodeRepository.findById(operationCode.getId())).thenReturn(dc);
		
		dcOperationCodeService.saveOrUpdate(operationCodeDto);
		
	}*/
}
