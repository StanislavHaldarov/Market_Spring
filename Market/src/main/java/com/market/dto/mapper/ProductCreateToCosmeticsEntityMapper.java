package com.market.dto.mapper;

import com.market.dto.ProductCreate;
import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToCosmeticsEntityMapper {
    public Cosmetic apply(ProductCreate productCreate, Product product) {

        return new Cosmetic(productCreate.getWeight(), productCreate.getVolume(), product);
    }

}
