package com.market.dto.mapper;

import com.market.dto.request.ProductItem;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductItemMapper {
    public ProductItem apply(Product product){
        ProductItem productItem = new ProductItem();
        productItem.setName(product.getName());
        productItem.setId(product.getId());
        productItem.setPriceBGN(product.getPriceBGN());
        productItem.setDescription(product.getDescription());
        productItem.setQuantity(1);
        productItem.setImageUrl(product.getImageUrl());

        return productItem;
    }
}
