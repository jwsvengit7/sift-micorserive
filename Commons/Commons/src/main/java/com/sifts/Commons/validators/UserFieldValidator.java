package com.sifts.Commons.validators;

import com.sifts.Commons.validators.annotation.ValidateAppFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UserFieldValidator implements ConstraintValidator<ValidateAppFields,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.isEmpty();
    }
}
