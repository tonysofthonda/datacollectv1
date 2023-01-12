/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.validation;

import com.honda.hdm.datacollect.validation.impl.AlphabeticValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author VJC80587
 */
@Documented
@Constraint(validatedBy = AlphabeticValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Alphabetic {
    String message() default "Only alphabetic characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
