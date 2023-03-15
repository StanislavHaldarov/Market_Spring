package com.market.service.product;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Food;

import java.util.List;


public interface FoodService {

    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);
    List<Food> findAllAvailable();


}
