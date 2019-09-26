package com.moviesmustsee.dtos.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.RetentionPolicy;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedExtensionValidator.class)

public @interface AllowedExtensions {
    String message() default "Extension is not supported, allowed extensions are: jpg,jpeg,png";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}