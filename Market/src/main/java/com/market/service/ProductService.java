package com.market.service;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> findAll();

    void saveProduct(ProductCreate productCreate);

    Product findProductById(Long id);

    void deleteProductById(Long id);
}

