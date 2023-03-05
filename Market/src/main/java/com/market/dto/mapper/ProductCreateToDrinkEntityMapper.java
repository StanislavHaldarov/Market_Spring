package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Food;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductCreateToDrinkEntityMapper {

    public Drink apply(ProductCreate productCreate){
        String description = getDescription(productCreate);

        return new Drink(productCreate.getName(),
                productCreate.getExpiredDate(),
                description,
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl(),
                productCreate.getVolume()
        );
    }

    private static String getDescription(ProductCreate productCreate) {
        String description = "обем: " + productCreate.getVolume() + "л\n";
        return description;
    }
}
