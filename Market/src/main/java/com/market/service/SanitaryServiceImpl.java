package com.market.service;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToSanitaryEntityMapper;
import com.market.repository.SanitaryRepository;
import org.springframework.stereotype.Service;


public class SanitaryServiceImpl implements SanitaryService {
    private final SanitaryRepository sanitaryRepository;
    private final ProductCreateToSanitaryEntityMapper productCreateToSanitaryEntityMapper;


    public SanitaryServiceImpl(SanitaryRepository sanitaryRepository, ProductCreateToSanitaryEntityMapper productCreateToSanitaryEntityMapper) {
        this.sanitaryRepository = sanitaryRepository;
        this.productCreateToSanitaryEntityMapper = productCreateToSanitaryEntityMapper;
    }

    public void save(ProductCreate productCreate){
        sanitaryRepository.save(productCreateToSanitaryEntityMapper.apply(productCreate));
    }
}
