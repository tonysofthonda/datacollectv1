package com.honda.hdm.datacollect.validation;


import com.honda.hdm.datacollect.validation.impl.FacilityDealerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FacilityDealerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FacilityDealerValid {
    String message() default "This dealer already have this facility";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
