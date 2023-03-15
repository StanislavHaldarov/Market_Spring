package com.market.dto.mapper;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Food;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToFoodEntityMapper {

    public Food apply(ProductCreate productCreate, Product product) {

        return new Food(productCreate.getWeight(), product);

    }


}
