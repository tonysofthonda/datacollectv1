package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerDto;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.validation.DealerValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DealerValidator implements ConstraintValidator<DealerValid, DcDealerDto> {

    @Autowired
    IDcDealerService dealerService;

    @Override
    public void initialize(DealerValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(DcDealerDto value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        Boolean isUpdating = value.getId() != null;
        DcDealer dealer = dealerService.findOneByDealerNumber(value.getDealerNumber());
        if ((dealer != null && !isUpdating) || (dealer != null && !dealer.getDealerNumber().equals(value.getDealerNumber()))) {
            context.buildConstraintViolationWithTemplate("Dealer with number " + value.getDealerNumber() + " already exists").addConstraintViolation();
            return false;
        }
        dealer = dealerService.findOneByName(value.getName());
        if ((dealer != null && !isUpdating) || (dealer != null && !dealer.getName().equals(value.getName()))) {
            context.buildConstraintViolationWithTemplate("Dealer with name '" + value.getName() + "' already exists").addConstraintViolation();
            return false;
        }
        return true;
    }
}