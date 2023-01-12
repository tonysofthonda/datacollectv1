package com.honda.hdm.datacollect.validation;

import com.honda.hdm.datacollect.validation.impl.DealerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DealerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DealerValid {
    String message() default "Dealer already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
