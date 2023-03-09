package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.Sanitary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductCreateToSanitaryEntityMapper {
    public Sanitary apply(ProductCreate productCreate, Product product) {

        return new Sanitary(productCreate.getCount(), product);
    }


}
