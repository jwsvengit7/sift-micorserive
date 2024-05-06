package com.sifts.Commons.validators.annotation;

import com.sifts.Commons.validators.UserFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserFieldValidator.class)
public @interface ValidateAppFields {
    public String message()  default "Input cannot be blank or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}