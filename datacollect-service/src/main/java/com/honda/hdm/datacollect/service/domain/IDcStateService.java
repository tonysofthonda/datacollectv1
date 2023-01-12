package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcState;
import com.honda.hdm.datacollect.model.entity.dto.DcStateDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface IDcStateService extends IRecordStatusableService<DcState, BigDecimal>,
        IDtoService<DcStateDto, BigDecimal>{

    public BigInteger findOneByName(String name) throws Exception;

    public List<DcStateDto> findAllDto();
}
