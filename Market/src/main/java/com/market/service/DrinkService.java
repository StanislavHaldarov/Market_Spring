package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Food;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DrinkService {
    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);
    public List<Drink> findAllAvailable();


}
