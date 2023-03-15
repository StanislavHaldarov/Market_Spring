package com.market.service.impl.product;

import com.market.dto.request.ProductCreate;
import com.market.dto.mapper.ProductCreateToProductEntityMapper;
import com.market.dto.mapper.ProductCreateToSanitaryEntityMapper;
import com.market.entity.productTypes.Sanitary;
import com.market.repository.product.SanitaryRepository;
import com.market.service.product.SanitaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SanitaryServiceImpl implements SanitaryService {
    private final SanitaryRepository sanitaryRepository;
    private final ProductCreateToSanitaryEntityMapper productCreateToSanitaryEntityMapper;
    private final ProductCreateToProductEntityMapper productCreateToProductEntityMapper;


    public SanitaryServiceImpl(SanitaryRepository sanitaryRepository, ProductCreateToSanitaryEntityMapper productCreateToSanitaryEntityMapper, ProductCreateToProductEntityMapper productCreateToProductEntityMapper) {
        this.sanitaryRepository = sanitaryRepository;
        this.productCreateToSanitaryEntityMapper = productCreateToSanitaryEntityMapper;
        this.productCreateToProductEntityMapper = productCreateToProductEntityMapper;
    }

    public void save(ProductCreate productCreate){
        sanitaryRepository.save(productCreateToSanitaryEntityMapper.apply(productCreate, productCreateToProductEntityMapper.apply(productCreate)));
    }

    @Override
    public void update(ProductCreate productCreate) {
        Sanitary sanitary = sanitaryRepository.findSanitaryByProductId(productCreate.getId());
        sanitary.setCount(productCreate.getCount());
        sanitary.setProduct(productCreateToProductEntityMapper.apply(productCreate));
        sanitaryRepository.save(sanitary);
    }

    @Override
    public List<Sanitary> findAllAvailable() {
        return sanitaryRepository.findAll();
    }

//    @Override
//    public List<Sanitary> findAllWithAvailableQuantityMoreThanZero() {
//        return sanitaryRepository.findAllWithAvailableQuantityMoreThanZero();
//    }
}
