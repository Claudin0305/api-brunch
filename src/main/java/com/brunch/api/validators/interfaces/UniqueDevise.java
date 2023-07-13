package com.brunch.api.validators.interfaces;


import com.brunch.api.validators.classes.UniqueDeviseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDeviseValidator.class)
public @interface UniqueDevise {
    String message() default "Devise already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}