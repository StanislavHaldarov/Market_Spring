package com.market.controller;

import com.market.entity.productTypes.Product;
import com.market.service.product.FoodService;
import com.market.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FoodController {
    private final FoodService foodService;
    private final ProductService productService;

    public FoodController(FoodService foodService, ProductService productService) {
        this.foodService = foodService;
        this.productService = productService;
    }


    @GetMapping("/food")
    public String getAll(Model model){
       List<Product> products= foodService.findAllAvailable().stream().map(f -> f.getProduct()).toList();
        model.addAttribute("products", products);
        return "products";
    }


}
