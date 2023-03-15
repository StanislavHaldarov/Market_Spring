package com.market.controller;

import com.market.entity.productTypes.Product;
import com.market.service.product.SanitaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SanitaryController {
    private final SanitaryService sanitaryService;

    public SanitaryController(SanitaryService sanitaryService) {
        this.sanitaryService = sanitaryService;
    }

    @GetMapping("/sanitary")
    public String getAll(Model model) {
        List<Product> productList = sanitaryService.findAllAvailable().stream().map(s-> s.getProduct()).toList();
        model.addAttribute("products", productList);
        return "products";
    }
}
