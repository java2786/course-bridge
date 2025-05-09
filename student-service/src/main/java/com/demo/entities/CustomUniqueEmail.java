package com.demo.entities;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomUniqueEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomUniqueEmail {
    String message() default "Email must end with @gmail.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
