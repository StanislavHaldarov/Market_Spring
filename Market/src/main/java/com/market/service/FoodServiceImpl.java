package com.market.service;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToFoodEntityMapper;
import com.market.repository.FoodRepository;
import org.springframework.stereotype.Service;


public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final ProductCreateToFoodEntityMapper productCreateToFoodEntityMapper;

    public FoodServiceImpl(FoodRepository foodRepository, ProductCreateToFoodEntityMapper productCreateToFoodEntityMapper) {
        this.foodRepository = foodRepository;
        this.productCreateToFoodEntityMapper = productCreateToFoodEntityMapper;
    }

    public void save(ProductCreate productCreate){
        foodRepository.save(productCreateToFoodEntityMapper.apply(productCreate));
    }
}
