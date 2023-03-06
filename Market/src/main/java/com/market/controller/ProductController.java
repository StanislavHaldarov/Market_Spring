package com.market.controller;

import com.market.dto.ProductCreate;
import com.market.service.ProductServiceImpl;
import com.market.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final ProductServiceImpl productServiceImpl;
    private final TypeService typeService;


    public ProductController(ProductServiceImpl productServiceImpl, TypeService typeService) {
        this.productServiceImpl = productServiceImpl;
        this.typeService = typeService;
    }
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productServiceImpl.findAll());
        return "products";
    }
    @GetMapping("/save")
    public String addProduct(Model model){
        model.addAttribute("types", typeService.findAllTypes());
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new ProductCreate());
        }

        return "addproduct";
    }

    @PostMapping("/save")
    public ModelAndView createProduct(@Valid @ModelAttribute ProductCreate product,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return new ModelAndView("redirect:/save");
        }

        productServiceImpl.saveProduct(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        productServiceImpl.deleteProductById(id);
        return new ModelAndView("redirect:/products");
    }

}
