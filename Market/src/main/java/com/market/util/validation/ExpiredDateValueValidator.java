package com.market.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ExpiredDateValueValidator implements ConstraintValidator<ValidateExpiredDateValue, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate == null){
            return true;
        }
        LocalDate now = LocalDate.now();
        return localDate.isAfter(now) || localDate.isEqual(now);
    }
}
