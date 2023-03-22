package com.market.dto.mapper;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Food;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.Sanitary;
import com.market.util.enums.ProductTypeEnum;
import com.market.repository.product.CosmeticRepository;
import com.market.repository.product.DrinkRepository;
import com.market.repository.product.FoodRepository;
import com.market.repository.product.SanitaryRepository;
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
                Food foodProduct = foodRepository.findFoodByProductId(product.getId());
                productCreate.setWeight(foodProduct.getWeight());
                productCreate.setCount(foodProduct.getCount());
                break;
            case "DRINKS":
                productCreate.setVolume(drinkRepository.findDrinkByProductId(product.getId()).getVolume());
                break;
            case "COSMETICS":
                Cosmetic cosmeticProduct = cosmeticRepository.findCosmeticByProductId(product.getId());
                productCreate.setVolume(cosmeticProduct.getVolume());
                productCreate.setWeight(cosmeticProduct.getWeight());
                break;
            case "SANITARY":
                Sanitary sanitaryProduct = sanitaryRepository.findSanitaryByProductId(product.getId());
                productCreate.setCount(sanitaryProduct.getCount());
                productCreate.setVolume(sanitaryProduct.getVolume());
                productCreate.setWeight(sanitaryProduct.getWeight());
                break;
            case "OTHERS":
                productCreate.setDescription(product.getDescription());
                break;

        }

        productCreate.setId(product.getId());
        productCreate.setType(product.getType());
        productCreate.setName(product.getName());
        productCreate.setExpiredDate(product.getExpiredDate());
        productCreate.setAvailableQuantity(product.getAvailableQuantity());
        productCreate.setPriceBGN(product.getPriceBGN());
        productCreate.setImageUrl(product.getImageUrl());

        return productCreate;

    }

}
