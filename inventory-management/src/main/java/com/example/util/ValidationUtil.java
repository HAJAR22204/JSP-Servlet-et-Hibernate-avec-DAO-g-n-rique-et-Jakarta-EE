package com.example.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {

    private static final ValidatorFactory factory =
            Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> Map<String, String> validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> v : violations) {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        }
        return errors;
    }
}