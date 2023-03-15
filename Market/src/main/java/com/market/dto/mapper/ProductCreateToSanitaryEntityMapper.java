package com.market.dto.mapper;

import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.Sanitary;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateToSanitaryEntityMapper {
    public Sanitary apply(ProductCreate productCreate, Product product) {

        return new Sanitary(productCreate.getCount(), product);
    }


}
