package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcLdapGroupDto;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcLdapGroupService;
import com.honda.hdm.datacollect.validation.LdapGroupValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LdapGroupValidator implements ConstraintValidator<LdapGroupValid, DcLdapGroupDto>{

    @Autowired
    DcLdapGroupService ldapGroupService;

    @Override
    public void initialize(LdapGroupValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(DcLdapGroupDto value, ConstraintValidatorContext context) {
        Boolean isUpdating = value.getId() != null;
        DcLdapGroup ldapGroup = ldapGroupService.findOneByLdapId(value.getLdapId());
        return (isUpdating && ldapGroup == null) || (isUpdating && ldapGroup.getLdapId().equals(value.getLdapId()));
    }
}
