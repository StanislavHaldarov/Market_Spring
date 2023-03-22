package com.market.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Documented
@Constraint(validatedBy = FoodValidator.class)
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateFood {
    String message() default "моля попълнете полето";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
