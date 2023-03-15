package com.market.service.impl.product;

import com.market.dto.request.ProductCreate;
import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.dto.mapper.ProductCreateToCosmeticsEntityMapper;
import com.market.entity.productTypes.Cosmetic;
import com.market.repository.product.CosmeticRepository;
import com.market.service.product.CosmeticService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmeticServiceImpl implements CosmeticService {
    private final CosmeticRepository cosmeticRepository;
    private final ProductCreateToCosmeticsEntityMapper productCreateToCosmeticsEntityMapper;
    private final ProductCreateToProductEntityMapper productCreateToProductEntityMapper;


    public CosmeticServiceImpl(CosmeticRepository cosmeticRepository, ProductCreateToCosmeticsEntityMapper productCreateToCosmeticsEntityMapper, ProductCreateToProductEntityMapper productCreateToProductEntityMapper) {
        this.cosmeticRepository = cosmeticRepository;
        this.productCreateToCosmeticsEntityMapper = productCreateToCosmeticsEntityMapper;
        this.productCreateToProductEntityMapper = productCreateToProductEntityMapper;
    }

    public void save(ProductCreate productCreate) {
        cosmeticRepository.save(productCreateToCosmeticsEntityMapper.apply(productCreate, productCreateToProductEntityMapper.apply(productCreate)));
    }

    @Override
    public void update(ProductCreate productCreate) {
        Cosmetic cosmetic = cosmeticRepository.findCosmeticByProductId(productCreate.getId());
        cosmetic.setWeight(productCreate.getWeight());
        cosmetic.setVolume(productCreate.getVolume());
        cosmetic.setProduct(productCreateToProductEntityMapper.apply(productCreate));
        cosmeticRepository.save(cosmetic);
    }

    @Override
    public List<Cosmetic> findAllAvailable() {
        return cosmeticRepository.findAll();
    }

}
