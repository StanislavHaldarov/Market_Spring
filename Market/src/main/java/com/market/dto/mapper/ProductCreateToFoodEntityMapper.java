package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Food;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToFoodEntityMapper {

    public Food apply(ProductCreate productCreate) {
        String description = getDescription(productCreate);

        return new Food(productCreate.getName(),
                productCreate.getExpiredDate(),
                description,
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl(),
                productCreate.getWeight()
        );
    }

    private static String getDescription(ProductCreate productCreate) {
        String description = "грамаж: " + productCreate.getWeight() + "кг\n";
        return description;
    }


}
