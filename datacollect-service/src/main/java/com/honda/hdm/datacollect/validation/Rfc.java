package com.honda.hdm.datacollect.validation;

import com.honda.hdm.datacollect.validation.impl.RfcValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RfcValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rfc {
    String message() default "Rfc not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
