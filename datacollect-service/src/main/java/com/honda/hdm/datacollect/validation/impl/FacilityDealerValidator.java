package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityXDealerDto;
import com.honda.hdm.datacollect.service.domain.IDcFacilityXDealerService;
import com.honda.hdm.datacollect.validation.FacilityDealerValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FacilityDealerValidator implements ConstraintValidator<FacilityDealerValid, DcFacilityXDealerDto> {

    @Autowired
    IDcFacilityXDealerService facilityXDealerService;

    @Override
    public void initialize(FacilityDealerValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(DcFacilityXDealerDto value, ConstraintValidatorContext context) {
        Boolean notExists = facilityXDealerService.findOneDto(new DcFacilityXDealerPK(value.getFacility().getId(), value.getDealer().getId())) == null;
        return notExists;
    }
}
