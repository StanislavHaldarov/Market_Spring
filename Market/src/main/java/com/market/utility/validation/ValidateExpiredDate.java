package com.market.utility.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExpiredDateValidator.class)
public @interface ValidateExpiredDate {
    String message() default "Невалиден срок на годност. Трябва да е не по-рано от днешна дата";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
