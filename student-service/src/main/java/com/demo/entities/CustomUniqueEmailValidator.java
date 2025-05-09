package com.demo.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomUniqueEmailValidator implements ConstraintValidator<CustomUniqueEmail, String> {

    private static final Logger logger = LoggerFactory.getLogger(CustomUniqueEmailValidator.class);
    
    @Override
    public void initialize(CustomUniqueEmail constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        logger.debug("student's email: #"+ email);

        if (email == null) {
            return false;
        }
        return email.endsWith("@gmail.com");
    }
}

