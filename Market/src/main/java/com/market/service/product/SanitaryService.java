package com.market.service.product;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Sanitary;

import java.util.List;


public interface SanitaryService {
    void save(ProductCreate productCreate);
    void update(ProductCreate productCreate);
    List<Sanitary> findAllAvailable();

}
