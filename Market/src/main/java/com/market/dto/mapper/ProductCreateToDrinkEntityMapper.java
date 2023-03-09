package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Food;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductCreateToDrinkEntityMapper {

    public Drink apply(ProductCreate productCreate, Product product){

       return new Drink(productCreate.getVolume(), product);

    }

}
