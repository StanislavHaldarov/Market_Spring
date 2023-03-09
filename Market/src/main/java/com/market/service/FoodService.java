package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Food;

import java.util.List;


public interface FoodService {

    void save(ProductCreate productCreate);
    void updateFood(ProductCreate productCreate);
    List<Food> findAllAvailable();


}
