package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcRoleDto;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcRoleService;
import com.honda.hdm.datacollect.validation.RoleValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<RoleValid, DcRoleDto> {

    @Autowired
    DcRoleService roleService;

    @Override
    public void initialize(RoleValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(DcRoleDto value, ConstraintValidatorContext context) {
        Boolean isUpdating = value.getId() != null;
        DcRole role = roleService.findByName(value.getName());
        return role == null || (isUpdating && role.getName().equals(value.getName()));
    }
}
