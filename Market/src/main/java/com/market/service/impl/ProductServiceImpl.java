package com.market.service.impl;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.repository.ProductRepository;
import com.market.service.*;
import com.market.utility.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductServiceImpl<P extends Product> implements ProductService {
    private final ProductRepository productRepository;
    private final FoodService foodService;
    private final DrinkService drinkService;
    private final CosmeticService cosmeticService;
    private final SanitaryService sanitaryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              FoodService foodService,
                              DrinkService drinkService,
                              CosmeticService cosmeticService,
                              SanitaryService sanitaryService) {
        this.productRepository = productRepository;
        this.foodService = foodService;
        this.drinkService = drinkService;
        this.cosmeticService = cosmeticService;
        this.sanitaryService = sanitaryService;

    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllWithAvailableQuantityMoreThanZero() {
        return productRepository.findAllAvailable();
    }

    @Override
    public void saveProduct(ProductCreate productCreate) {

        switch (productCreate.getType()) {
            case "FOOD":
                foodService.save(productCreate);
                break;
            case "DRINKS":
                drinkService.save(productCreate);
                break;
            case "SANITARY":
                sanitaryService.save(productCreate);
                break;
            case "COSMETICS":
                cosmeticService.save(productCreate);
                break;
        }
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(ProductCreate productCreate) {
        switch (productCreate.getType()) {
            case "FOOD":
                foodService.updateFood(productCreate);
                break;
            case "DRINKS":
                drinkService.update(productCreate);
                break;
            case "SANITARY":
                sanitaryService.update(productCreate);
                break;
            case "COSMETICS":
                cosmeticService.update(productCreate);
                break;
        }
    }


    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product with " + id + " was not found"));
    }


}
