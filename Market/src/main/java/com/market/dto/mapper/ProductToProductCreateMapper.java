package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.ProductTypeEnum;
import com.market.repository.CosmeticRepository;
import com.market.repository.DrinkRepository;
import com.market.repository.FoodRepository;
import com.market.repository.SanitaryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductCreateMapper {
    private final FoodRepository foodRepository;
    private final DrinkRepository drinkRepository;
    private final CosmeticRepository cosmeticRepository;
    private final SanitaryRepository sanitaryRepository;

    public ProductToProductCreateMapper(FoodRepository foodRepository, DrinkRepository drinkRepository, CosmeticRepository cosmeticRepository, SanitaryRepository sanitaryRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.cosmeticRepository = cosmeticRepository;
        this.sanitaryRepository = sanitaryRepository;
    }

    public ProductCreate apply(Product product) {
        ProductCreate productCreate = new ProductCreate();
        ProductTypeEnum type = product.getType();

        switch (type.name()) {
            case "FOOD":
                productCreate.setWeight(foodRepository.findFoodByProductId(product.getId()).getWeight());
                break;
            case "DRINK":
                productCreate.setVolume(drinkRepository.findDrinkByProductId(product.getId()).getVolume());
                break;
            case "COSMETIC":
                productCreate.setVolume(cosmeticRepository.findCosmeticByProductId(product.getId()).getVolume());
                productCreate.setWeight(cosmeticRepository.findCosmeticByProductId(product.getId()).getWeight());
                break;
            case "SANITARY":
                productCreate.setCount(sanitaryRepository.findSanitaryByProductId(product.getId()).getCount());
                break;

        }
        productCreate.setId(product.getId());
        productCreate.setType(product.getType().name());
        productCreate.setName(product.getName());
        productCreate.setAvailableQuantity(product.getAvailableQuantity());
        productCreate.setPriceBGN(product.getPriceBGN());
        productCreate.setImageUrl(product.getImageUrl());
        productCreate.setExpiredDate(product.getExpiredDate());

        return productCreate;

    }

}
