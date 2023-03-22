package com.market.service.impl.product;

import com.market.dto.request.ProductCreate;
import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.dto.mapper.ProductCreateToFoodEntityMapper;
import com.market.entity.productTypes.Food;
import com.market.repository.product.FoodRepository;
import com.market.service.product.FoodService;
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
        foodRepository.save(productCreateToFoodEntityMapper
                .apply(productCreate,
                        productCreateToProductEntityMapper.apply(productCreate)));
    }


    @Override
    public void update(ProductCreate productCreate) {
        Food food = foodRepository.findFoodByProductId(productCreate.getId());
        food.setWeight(productCreate.getWeight());
        food.setCount(productCreate.getCount());
        food.setProduct(productCreateToProductEntityMapper.apply(productCreate));
        foodRepository.save(food);
    }

}
