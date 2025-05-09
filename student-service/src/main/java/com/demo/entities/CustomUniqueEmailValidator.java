package com.demo.entities;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomUniqueEmailValidator implements ConstraintValidator<CustomUniqueEmail, String> {

    @Override
    public void initialize(CustomUniqueEmail constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        return email.endsWith("@gmail.com");
    }
}

