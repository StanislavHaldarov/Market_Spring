package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.Filter;
import com.market.entity.productTypes.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface ProductService {
    List<Product> findAll();

    List<Product> findAllWithAvailableQuantityMoreThanZero();

    Product findProductById(Long id);

    void saveProduct(ProductCreate productCreate);

    void updateProduct(ProductCreate productCreate);

    void deleteProductById(Long id);


    List<Product> findAllWithSpecification(Filter filter);
}

