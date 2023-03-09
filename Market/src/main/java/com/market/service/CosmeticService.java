package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Food;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CosmeticService {
    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);
    List<Cosmetic> findAllAvailable();

}
