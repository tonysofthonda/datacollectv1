package com.honda.hdm.datacollect.validation.impl;

import com.honda.hdm.datacollect.validation.Rfc;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RfcValidator implements ConstraintValidator<Rfc, String> {
    @Override
    public void initialize(Rfc constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("[A-ZÑ&]{3,4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])(?:[A-Z\\d]{3})");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
