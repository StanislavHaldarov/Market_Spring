package com.market.dto.mapper;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToDrinkEntityMapper {

    public Drink apply(ProductCreate productCreate, Product product){

       return new Drink(productCreate.getVolume(), product);

    }

}
