package com.honda.hdm.datacollect.validation;

import com.honda.hdm.datacollect.validation.impl.LdapGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LdapGroupValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapGroupValid {
    String message() default "This ldap group id already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
