package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Drink;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductCreateToCosmeticsEntityMapper {
    public Cosmetic apply(ProductCreate productCreate){
        String description = getDescription(productCreate);


        return new Cosmetic(productCreate.getName(),
                productCreate.getExpiredDate(),
                description,
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl(),
                productCreate.getWeight(),
                productCreate.getVolume()
        );
    }

    private static String getDescription(ProductCreate productCreate) {
        StringBuilder stringBuilder = new StringBuilder();
        if(productCreate.getVolume() != null){
            stringBuilder.append("обем: " + productCreate.getVolume() + "л\n");
        }

        if(productCreate.getWeight() != null){
            stringBuilder.append("грамаж: " + productCreate.getVolume() + "кг\n");
        }
        return stringBuilder.toString();
    }}
