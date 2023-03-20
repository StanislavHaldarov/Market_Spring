package com.market.service.impl.product;

import com.market.dto.request.Filter;
import com.market.dto.request.ProductCreate;
import com.market.entity.order.OrderItem;
import com.market.entity.productTypes.Product;
import com.market.repository.product.ProductRepository;
import com.market.service.product.SpecificationProductFilter;
import com.market.service.order.OrderItemService;
import com.market.service.product.*;
import com.market.service.user.UserService;
import com.market.utility.enums.RoleNameEnum;
import com.market.utility.exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private final UserService userService;


    public ProductServiceImpl(ProductRepository productRepository,
                              FoodService foodService,
                              DrinkService drinkService,
                              CosmeticService cosmeticService,
                              SanitaryService sanitaryService, SpecificationProductFilter specificationProductFilter, OrderItemService orderItemService, UserService userService) {
        this.productRepository = productRepository;
        this.foodService = foodService;
        this.drinkService = drinkService;
        this.cosmeticService = cosmeticService;
        this.sanitaryService = sanitaryService;
        this.specificationProductFilter = specificationProductFilter;
        this.orderItemService = orderItemService;
        this.userService = userService;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllAvailable() {

        return productRepository.findAll(
                specificationProductFilter.withNotExpiredDate()
                        .and(specificationProductFilter.withAvailableQuantity())
        );
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
        List<Sort.Order> sorts = new ArrayList<>();
        if (!filter.getOrderBy().equals("null")) {
            if (filter.getOrderBy().equals("desc")) {

                sorts.add(new Sort.Order(Sort.Direction.DESC, "priceBGN"));
            } else if (filter.getOrderBy().equals("asc")) {

                sorts.add(new Sort.Order(Sort.Direction.ASC, "priceBGN"));

            } else {
                sorts.add(new Sort.Order(Sort.Direction.ASC, "expiredDate"));
            }
        }

        if (userService.findAuthenticatedUser() == null || userService.findAuthenticatedUser().getRole().getName() == RoleNameEnum.CUSTOMER ) {
            return productRepository.findAll(specificationProductFilter.filter(filter)
                    .and(specificationProductFilter.withAvailableQuantity()
                            .and(specificationProductFilter.withNotExpiredDate())), Sort.by(sorts));
        } else {
            return productRepository.findAll(specificationProductFilter.filter(filter), Sort.by(sorts));

        }
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
