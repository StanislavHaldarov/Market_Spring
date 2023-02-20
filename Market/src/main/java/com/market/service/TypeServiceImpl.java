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
        for (ProductTypeEnum t : ProductTypeEnum.values()) {
            if (typeRepository.findTypeByName(t) == null) {
                typeRepository.save(new Type(t));
            }
        }
    }
}
