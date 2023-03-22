package com.market.util.validation;

import com.market.dto.request.ProductCreate;
import com.market.util.enums.ProductTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FoodValidator implements ConstraintValidator<ValidateFood, ProductCreate> {


    @Override
    public boolean isValid(ProductCreate productCreate, ConstraintValidatorContext constraintValidatorContext) {
        if (productCreate.getType() == ProductTypeEnum.FOOD) {
            return
                    (productCreate.getWeight() == null && productCreate.getCount() != null
                            || productCreate.getWeight() != null && productCreate.getCount() == null
                            || productCreate.getWeight() != null && productCreate.getCount() != null) &&
                            productCreate.getExpiredDate() != null;
        }
        return true;
    }
}
