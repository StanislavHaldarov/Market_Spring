package com.market.service.impl;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToDrinkEntityMapper;
import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Product;
import com.market.repository.DrinkRepository;
import com.market.service.DrinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;
    private final ProductCreateToDrinkEntityMapper productCreateToDrinkEntityMapper;

    private final ProductCreateToProductEntityMapper productCreateToProductEntityMapper;


    public DrinkServiceImpl(DrinkRepository drinkRepository, ProductCreateToDrinkEntityMapper productCreateToDrinkEntityMapper, ProductCreateToProductEntityMapper productCreateToProductEntityMapper) {
        this.drinkRepository = drinkRepository;
        this.productCreateToDrinkEntityMapper = productCreateToDrinkEntityMapper;
        this.productCreateToProductEntityMapper = productCreateToProductEntityMapper;
    }

    public void save(ProductCreate productCreate) {
        Product product = productCreateToProductEntityMapper.apply(productCreate);
        productCreateToDrinkEntityMapper.apply(productCreate, product);
    }

    @Override
    public void update(ProductCreate productCreate) {
        Drink drink = drinkRepository.findDrinkByProductId(productCreate.getId());
        drink.setVolume(productCreate.getVolume());
        drink.setProduct(productCreateToProductEntityMapper.apply(productCreate));
        drinkRepository.save(drink);
    }

    @Override
    public List<Drink> findAllAvailable() {
        return drinkRepository.findAll();
    }

}
