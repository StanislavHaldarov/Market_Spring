package com.market.controller;

import com.market.dto.mapper.ProductToProductItemMapper;
import com.market.dto.request.ProductItem;
import com.market.entity.User;
import com.market.service.user.UserService;
import com.market.service.order.OrderService;
import com.market.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderItemController {
    private final ProductService productService;
    private final ProductToProductItemMapper productToProductItemMapper;
    private final OrderService orderService;
    private final UserService userService;


    public OrderItemController(ProductService productService, ProductToProductItemMapper productToProductItemMapper, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.productToProductItemMapper = productToProductItemMapper;
        this.orderService = orderService;
        this.userService = userService;
    }

    // Item Details - ok
    @GetMapping("/item/details/{id}")
    public String showItemDetails(Model model, @PathVariable Long id) {
        model.addAttribute("product", productToProductItemMapper.apply(productService.findProductById(id)));

        return "product-details";
    }

    //Delete Item
    @PostMapping("/delete/item/{id}")
    public ModelAndView deleteItem(@PathVariable Long id) {
        User user = userService.findAuthenticatedUser();
        orderService.deleteOrderItemById(id);
        return new ModelAndView("redirect:/orders/");
    }

    // Save Item - ok
    @PostMapping("/item/save")
    public ModelAndView saveItem(ProductItem productItem) {
//         find currently signed-in user
        User user = userService.findAuthenticatedUser();
        try {
            orderService.saveOrderItem(user, productItem);
        } catch (Exception e) {
            return new ModelAndView("product-details")
                    .addObject("product", productItem).addObject("quantityError", e.getMessage());
        }

        return new ModelAndView("redirect:/products/available");
    }

    // Update Item
    @PostMapping("/update/{id}")
    public ModelAndView updateOrderItem(@PathVariable Long id) {
//        TODO:implement update in orderService
        return new ModelAndView("redirect:/orders/");
    }

}
