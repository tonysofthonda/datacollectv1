package com.honda.hdm.datacollect.validation;

import com.honda.hdm.datacollect.validation.impl.RoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleValid {
    String message() default "This role name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
