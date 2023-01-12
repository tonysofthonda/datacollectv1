/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.model.entity.dto.DcOrderTypeDto;
import com.honda.hdm.datacollect.service.domain.IDcOrderTypeService;
import com.honda.hdm.datacollect.validation.OrderTypeValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author VJC80587
 */
public class OrderTypeValidator implements ConstraintValidator<OrderTypeValid, DcOrderTypeDto> {

    @Autowired
    IDcOrderTypeService service;

    @Override
    public void initialize(OrderTypeValid orderTypeValid) {
    }

    @Override
    public boolean isValid(DcOrderTypeDto value, ConstraintValidatorContext context) {
        try {
            DcOrderTypeDto orderType = service.findOneByCodeDto(value.getCode());
            Boolean isUpdating = value.getId() != null;
            if (!isUpdating || !orderType.getCode().equals(value.getCode())) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return true;
        }
    }
}
