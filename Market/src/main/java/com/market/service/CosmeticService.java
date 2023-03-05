package com.market.service;

import com.market.dto.ProductCreate;
import org.springframework.stereotype.Service;

@Service
public interface CosmeticService {
    void save(ProductCreate productCreate);
}
