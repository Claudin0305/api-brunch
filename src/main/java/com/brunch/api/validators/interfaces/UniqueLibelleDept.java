package com.brunch.api.validators.interfaces;

import com.brunch.api.validators.classes.UniqueLibelleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLibelleValidator.class)
public @interface UniqueLibelleDept {
    String message() default "Combination of field1 and field2 must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}