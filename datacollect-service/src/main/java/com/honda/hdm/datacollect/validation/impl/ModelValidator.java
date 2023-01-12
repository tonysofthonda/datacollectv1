/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.honda.hdm.datacollect.model.entity.dto.DcModelDto;
import com.honda.hdm.datacollect.service.domain.IDcModelService;
import com.honda.hdm.datacollect.validation.ModelValid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author VJC80587
 */
public class ModelValidator implements ConstraintValidator<ModelValid, DcModelDto> {

    @Autowired
    IDcModelService modelService;

    @Override
    public void initialize(ModelValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(DcModelDto model, ConstraintValidatorContext context) {
        Boolean isUpdating = model.getId() != null;

        DcModelDto modelExists = modelService.findOneByCodeAndYearDto(model.getCode(), model.getYear());
        context.disableDefaultConstraintViolation();
        if ((modelExists != null && !isUpdating) || (modelExists != null && (!modelExists.getCode().equals(model.getCode()) || !modelExists.getYear().equals(model.getYear())))) {
            context.buildConstraintViolationWithTemplate("ModelValid with code " + model.getCode() + " and year " + model.getYear() + " already exists").addConstraintViolation();
            return false;
        }
        Boolean forFinantialSystem = model.getSystemServices().stream().anyMatch(sy -> sy.getName().equals("Informacion Financiera"));
//        if (model.getAccountNumber() == null && forFinantialSystem) {
//            context.buildConstraintViolationWithTemplate("This model requires an account number").addConstraintViolation();
//            return false;
//        }
        return true;
    }
}


