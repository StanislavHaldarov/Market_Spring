package com.market.controller;

import com.market.entity.productTypes.Product;
import com.market.service.product.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/drink")
    public String getAll(Model model) {
        List<Product> products = drinkService.findAllAvailable().stream().map(d -> d.getProduct()).toList();
        model.addAttribute("products", products);
        return "products";
    }}
