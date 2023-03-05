package com.market.service;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToDrinkEntityMapper;
import com.market.repository.DrinkRepository;
import org.springframework.stereotype.Service;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;
    private final ProductCreateToDrinkEntityMapper productCreateToDrinkEntityMapper;


    public DrinkServiceImpl(DrinkRepository drinkRepository, ProductCreateToDrinkEntityMapper productCreateToDrinkEntityMapper) {
        this.drinkRepository = drinkRepository;
        this.productCreateToDrinkEntityMapper = productCreateToDrinkEntityMapper;
    }

    public void save(ProductCreate productCreate){
        drinkRepository.save(productCreateToDrinkEntityMapper.apply(productCreate));
    }
}
