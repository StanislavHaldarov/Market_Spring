package com.market.service.product;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Drink;

import java.util.List;

public interface DrinkService {
    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);
    public List<Drink> findAllAvailable();


}
