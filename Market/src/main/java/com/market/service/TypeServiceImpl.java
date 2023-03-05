package com.market.service;

import com.market.entity.productTypes.ProductTypeEnum;
import com.market.entity.productTypes.Type;
import com.market.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> findAllTypes(){
        return typeRepository.findAll();
    }


}
