package com.market.controller;

import com.market.dto.mapper.ProductToProductCreateMapper;
import com.market.dto.request.Filter;
import com.market.dto.request.ProductCreate;
import com.market.entity.productTypes.Product;
import com.market.service.product.ProductService;
import com.market.utility.enums.ProductTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductToProductCreateMapper productCreateMapper;


    public ProductController(ProductService productService, ProductToProductCreateMapper productCreateMapper) {
        this.productService = productService;
        this.productCreateMapper = productCreateMapper;

    }

    // All Products - OK
    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("filter", new Filter());
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    //Products with Specification - OK
    @GetMapping("/all/specification")
    public String getAllWithSpecification(Model model, Filter filter) {
        model.addAttribute("filter", filter);
        model.addAttribute("products", productService.findAllWithSpecification(filter));
        return "products";
    }


    // All Available Products - OK
    @GetMapping("/available")
    public String getAllAvailable(Model model) {
        model.addAttribute("filter", new Filter());
        model.addAttribute("products", productService.findAllAvailable());
        return "products";
    }

    // Add Product - OK
    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("types", ProductTypeEnum.values());
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new ProductCreate());
        }
        return "addproduct";
    }

    //    ADD PRODUCT - OK
    @PostMapping("/add")
    public ModelAndView createProduct(@Valid @ModelAttribute ProductCreate productCreate,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", productCreate);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return new ModelAndView("redirect:/products/add");
        }

        productService.saveProductCreate(productCreate);
        return new ModelAndView("redirect:/products/available");
    }

    // Delete Product - OK
    @PostMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ModelAndView("redirect:/products/available");
    }

    //    UPDATE PRODUCT - OK
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        try {
            Product product = productService.findProductById(id);
            ProductCreate productCreate = productCreateMapper.apply(product);
            model.addAttribute("product", productCreate);
            return "productmanagement";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "products";
        }
    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@Valid ProductCreate productCreate
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("productmanagement").
                    addObject("product", productCreate).
                    addObject("org.springframework.validation.BindingResult.product", bindingResult);
        }
        productService.updateProduct(productCreate);
        return new ModelAndView("redirect:/products/available");
    }


}
