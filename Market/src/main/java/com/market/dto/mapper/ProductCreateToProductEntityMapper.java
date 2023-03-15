package com.market.dto.mapper;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.utility.enums.ProductTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToProductEntityMapper {

    public Product apply(ProductCreate productCreate) {

        String description = getDescription(productCreate);
        return new Product(
                productCreate.getId(),
                productCreate.getName(),
                productCreate.getType(),
                description,
                productCreate.getExpiredDate(),
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl());

    }

    private String getDescription(ProductCreate productCreate) {
        String description = new String();
        switch (productCreate.getType().name()) {
            case "FOOD":

                description = "грамаж: " + productCreate.getWeight() + "кг";
                break;
            case "DRINKS":

                description = "обем: " + productCreate.getVolume() + "л";
                break;
            case "COSMETICS":
//                TODO: replace it with StringBuilder
                if(productCreate.getWeight() != null){
                    description = "грамаж: " + productCreate.getWeight() + "кг\n";
                }
                if(productCreate.getVolume() != null){
                    description += "обем: " + productCreate.getVolume() + "л";
                }
                break;
            case "SANITARY":
                description = "брой в пакет: " +  productCreate.getCount();
                break;
        }
        return description;
    }

}
