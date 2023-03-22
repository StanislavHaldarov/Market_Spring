package com.market.util.validation;

import com.market.dto.request.ProductCreate;
import com.market.util.enums.ProductTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CosmeticsValidator implements ConstraintValidator<ValidateCosmetics, ProductCreate> {
    @Override
    public boolean isValid(ProductCreate productCreate, ConstraintValidatorContext constraintValidatorContext) {
        if (productCreate.getType() == ProductTypeEnum.COSMETICS) {
            return
                    productCreate.getWeight() == null && productCreate.getVolume() != null
                            || productCreate.getWeight() != null && productCreate.getVolume() == null
                    && productCreate.getExpiredDate() != null;

        }
        return true;
    }
}
