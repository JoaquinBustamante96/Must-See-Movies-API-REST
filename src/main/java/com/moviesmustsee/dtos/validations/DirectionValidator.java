package com.moviesmustsee.dtos.validations;


import org.springframework.data.domain.Sort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DirectionValidator implements ConstraintValidator<DirectionValidate, String> {

    @Override
    public void initialize(DirectionValidate constraint) {
        // Empty, not operation
    }

    @Override
    public boolean isValid(String direction, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        if(Sort.Direction.fromOptionalString(direction).isPresent()){
            isValid = true;
        }
        return isValid;
    }
}
