/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.validation.Alphanumeric;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author VJC80587
 */
public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    @Override
    public void initialize(Alphanumeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[A-Za-z\\d\\s]*$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
