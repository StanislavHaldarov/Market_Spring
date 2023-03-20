package com.market.utility.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ExpiredDateValidator implements ConstraintValidator<ValidateExpiredDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate == null){
            return true;
        }
        LocalDate now = LocalDate.now();
        return localDate.isAfter(now) || localDate.isEqual(now);
    }
}
