package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.ProductTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToProductEntityMapper {

    public Product apply(ProductCreate productCreate) {

        String description = getDescription(productCreate);
        return new Product(
                productCreate.getId(),
                productCreate.getName(),
                ProductTypeEnum.valueOf(productCreate.getType()),
                description,
                productCreate.getExpiredDate(),
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl());

    }

    private String getDescription(ProductCreate productCreate) {
        String description = new String();
        switch (productCreate.getType()) {
            case "FOOD":
                description = "грамаж: " + productCreate.getWeight() + "кг";
                break;
            case "DRINK":
                description = "обем: " + productCreate.getVolume() + "л";
                break;
            case "COSMETIC":
                description = "грамаж: " + productCreate.getWeight() + "кг\nобем: " + productCreate.getVolume();
                break;
            case "SANITARY":
                description = productCreate.getCount() + "брой в пакет";
                break;

        }
        return description;
    }

}
