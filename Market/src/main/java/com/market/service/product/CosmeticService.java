package com.market.service.product;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Cosmetic;

import java.util.List;


public interface CosmeticService {
    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);

}
