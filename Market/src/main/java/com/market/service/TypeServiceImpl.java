package com.market.service;

import com.market.entity.productTypes.ProductTypeEnum;
import com.market.entity.productTypes.Type;
import com.market.repository.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void initTypes() {
        if (typeRepository.count() == 0){
            Type food = new Type(ProductTypeEnum.FOOD);
            Type drinks = new Type(ProductTypeEnum.DRINKS);
            Type sanitary = new Type(ProductTypeEnum.SANITARY);
            Type cosmetics = new Type(ProductTypeEnum.COSMETICS);
            typeRepository.save(food);
            typeRepository.save(drinks);
            typeRepository.save(sanitary);
            typeRepository.save(cosmetics);
        }
    }
}
