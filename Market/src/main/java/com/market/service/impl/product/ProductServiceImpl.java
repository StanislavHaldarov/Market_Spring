package com.market.service.impl.product;

import com.market.dto.request.Filter;
import com.market.dto.request.ProductCreate;
import com.market.entity.order.OrderItem;
import com.market.entity.productTypes.Product;
import com.market.repository.product.ProductRepository;
import com.market.service.product.SpecificationProductFilter;
import com.market.service.order.OrderItemService;
import com.market.service.product.*;
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
    private final SpecificationProductFilter specificationProductFilter;
    private final OrderItemService orderItemService;

    public ProductServiceImpl(ProductRepository productRepository,
                              FoodService foodService,
                              DrinkService drinkService,
                              CosmeticService cosmeticService,
                              SanitaryService sanitaryService, SpecificationProductFilter specificationProductFilter, OrderItemService orderItemService) {
        this.productRepository = productRepository;
        this.foodService = foodService;
        this.drinkService = drinkService;
        this.cosmeticService = cosmeticService;
        this.sanitaryService = sanitaryService;
        this.specificationProductFilter = specificationProductFilter;
        this.orderItemService = orderItemService;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllWithAvailableQuantityMoreThanZero() {
        return productRepository.findAllAvailable();
    }

    //    SAVE PRODUCT CREATE DTO - OK
    @Override
    public void saveProductCreate(ProductCreate productCreate) {

        switch (productCreate.getType().name()) {
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

    //    DELETE PRODUCT BY ID - OK
    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new NotFoundException("Продуктът не е намерен");
        }

        for (OrderItem item : product.getItems()) {
            item.setProduct(null);
            item.setQuantity(0);
            orderItemService.saveItem(item);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllWithSpecification(Filter filter) {
        return productRepository.findAll(specificationProductFilter.filter(filter));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductCreate productCreate) {
        switch (productCreate.getType().name()) {
            case "FOOD":
                foodService.update(productCreate);
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
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("product with " + id + " was not found"));
    }


}
