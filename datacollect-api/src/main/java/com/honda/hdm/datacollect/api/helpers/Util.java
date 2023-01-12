package com.honda.hdm.datacollect.api.helpers;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class Util {

    public static Map<String, ?> getAllErrorsOfRequest(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        errors.put("msg", "Fields with errors");
        result.getFieldErrors().stream().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        if (result.getFieldErrors().isEmpty()) {
            result.getAllErrors().stream().forEach(error -> {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            });
        }
        return errors;
    }
}
