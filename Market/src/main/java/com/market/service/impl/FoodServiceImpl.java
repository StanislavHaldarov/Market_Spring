package com.market.service.impl;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.dto.mapper.ProductCreateToFoodEntityMapper;
import com.market.entity.productTypes.Food;
import com.market.repository.FoodRepository;
import com.market.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final ProductCreateToFoodEntityMapper productCreateToFoodEntityMapper;
    private final ProductCreateToProductEntityMapper productCreateToProductEntityMapper;

    public FoodServiceImpl(FoodRepository foodRepository, ProductCreateToFoodEntityMapper productCreateToFoodEntityMapper, ProductCreateToProductEntityMapper productCreateToProductEntityMapper) {
        this.foodRepository = foodRepository;
        this.productCreateToFoodEntityMapper = productCreateToFoodEntityMapper;
        this.productCreateToProductEntityMapper = productCreateToProductEntityMapper;
    }

    @Override
    public void save(ProductCreate productCreate){
        foodRepository.save(productCreateToFoodEntityMapper.apply(productCreate, productCreateToProductEntityMapper.apply(productCreate)));
    }


    @Override
    public List<Food> findAllAvailable() {
        return foodRepository.findAll();
    }

    @Override
    public void updateFood(ProductCreate productCreate) {
        Food food = foodRepository.findFoodByProductId(productCreate.getId());
        food.setWeight(productCreate.getWeight());
        food.setProduct(productCreateToProductEntityMapper.apply(productCreate));
        foodRepository.save(food);
    }

//    @Override
//    public Food findById(Long id) {
//        return foodRepository.findById(id).orElseThrow(() -> new NotFoundException("храна с такова ид не съществува"));
//    }
}
