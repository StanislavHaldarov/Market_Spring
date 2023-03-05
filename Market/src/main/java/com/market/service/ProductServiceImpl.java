package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.repository.ProductRepository;
import com.market.utility.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FoodServiceImpl foodServiceImpl;
    private final DrinkServiceImpl drinkServiceImpl;
    private final CosmeticServiceImpl cosmeticServiceImpl;
    private final SanitaryServiceImpl sanitaryServiceImpl;

    public ProductServiceImpl(ProductRepository productRepository,
                              FoodServiceImpl foodServiceImpl,
                              DrinkServiceImpl drinkServiceImpl,
                              CosmeticServiceImpl cosmeticServiceImpl,
                              SanitaryServiceImpl sanitaryServiceImpl) {
        this.productRepository = productRepository;
        this.foodServiceImpl = foodServiceImpl;
        this.drinkServiceImpl = drinkServiceImpl;
        this.cosmeticServiceImpl = cosmeticServiceImpl;
        this.sanitaryServiceImpl = sanitaryServiceImpl;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void saveProduct(ProductCreate productCreate) {
        switch (productCreate.getType()) {
            case "FOOD":
                foodServiceImpl.save(productCreate);
                break;
            case "DRINKS":
                drinkServiceImpl.save(productCreate);
                break;
            case "SANITARY":
                sanitaryServiceImpl.save(productCreate);
                break;
            case "COSMETICS":
                cosmeticServiceImpl.save(productCreate);
                break;
        }
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product with " + id + " was not found"));
    }


    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
