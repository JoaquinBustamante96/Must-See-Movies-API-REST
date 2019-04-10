package com.first.demoMongo.dtos.validations;

import com.first.demoMongo.dtos.validations.DirectionValidate;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DirectionValidator.class)
public @interface DirectionValidate {
    String message() default "Invalid direction,valid directions are: ASC or DESC";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}