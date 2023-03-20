package com.market.service.product;

import com.market.dto.request.ProductCreate;
import com.market.dto.request.Filter;
import com.market.entity.productTypes.Product;

import java.util.List;


public interface ProductService {
    List<Product> findAll();

    List<Product> findAllAvailable();

    Product findProductById(Long id);

    void saveProductCreate(ProductCreate productCreate);

    void updateProduct(ProductCreate productCreate);

    void deleteProductById(Long id);

    List<Product> findAllWithSpecification(Filter filter);

    void saveProduct(Product product);
}

