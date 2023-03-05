package com.market.service;

import com.market.dto.ProductCreate;
import com.market.dto.mapper.ProductCreateToCosmeticsEntityMapper;
import com.market.repository.CosmeticRepository;


public class CosmeticServiceImpl implements CosmeticService {
    private final CosmeticRepository cosmeticRepository;
    private final ProductCreateToCosmeticsEntityMapper productCreateToCosmeticsEntityMapper;


    public CosmeticServiceImpl(CosmeticRepository cosmeticRepository, ProductCreateToCosmeticsEntityMapper productCreateToCosmeticsEntityMapper) {
        this.cosmeticRepository = cosmeticRepository;
        this.productCreateToCosmeticsEntityMapper = productCreateToCosmeticsEntityMapper;
    }

    public void save(ProductCreate productCreate) {
        cosmeticRepository.save(productCreateToCosmeticsEntityMapper.apply(productCreate));
    }
}
