package com.market.controller;

import com.market.entity.productTypes.Product;
import com.market.service.CosmeticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CosmeticController {
    private final CosmeticService cosmeticService;

    public CosmeticController(CosmeticService cosmeticService) {
        this.cosmeticService = cosmeticService;
    }

    @GetMapping("/cosmetic")
    public String getAll(Model model) {
        List<Product> products = cosmeticService.findAllAvailable().stream().map(c -> c.getProduct()).toList();
        model.addAttribute("products", products);
        return "products";
    }
}
