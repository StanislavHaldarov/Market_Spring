package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Sanitary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductCreateToSanitaryEntityMapper {
    public Sanitary apply(ProductCreate productCreate){
        String description = getDescription(productCreate);

        return new Sanitary(productCreate.getName(),
                productCreate.getExpiredDate(),
                description,
                productCreate.getAvailableQuantity(),
                productCreate.getPriceBGN(),
                productCreate.getImageUrl(),
                productCreate.getCount()
        );
    }

    private String getDescription(ProductCreate productCreate) {
        String description = "брой в опаковка: " + productCreate.getCount() + "\n";
        return description;

    }
}
