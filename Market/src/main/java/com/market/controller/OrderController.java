package com.market.controller;

import com.market.entity.User;
import com.market.entity.order.Order;
import com.market.service.MailService;
import com.market.service.order.InvoiceService;
import com.market.service.order.OrderService;
import com.market.service.user.UserService;
import com.market.utility.enums.OrderStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    private final InvoiceService invoiceService;
    private final MailService mailService;

    public OrderController(UserService userService, OrderService orderService, InvoiceService invoiceService, MailService mailService) {
        this.userService = userService;
        this.orderService = orderService;
        this.invoiceService = invoiceService;
        this.mailService = mailService;
    }

//    @GetMapping("/all")
//    public String allOrders(Model model) {
//        List<Order> orders = orderService.findAllOrders();
//        model.addAttribute("orders", orderService.findAllOrders());
//        model.addAttribute("statuses", OrderStatusEnum.values());
//        return "orders-management";
//    }


    // Retrieve User order
    @GetMapping("/")
    public String getOrder(Model model) {
        // find currently signed-in user
        User user = userService.findAuthenticatedUser();
        // find user active order
        Order order = orderService.findActiveOrderByUserIdAndStatus(user.getId(), OrderStatusEnum.NEW);
        if (order != null) {
            order.setTotalPrice(order.findTotalPrice());
        }
        model.addAttribute("order", order);
        return "order";
    }


    // Make Order
    @PostMapping("/make/order/{orderId}")
    public ModelAndView makeOrder(@PathVariable Long orderId) throws Exception {
        Order order = orderService.findOrderById(orderId);

        if (order.checkIfOrderCanBeMade()) {
            orderService.submitOrder(orderId);
            return new ModelAndView("redirect:/orders/");
        } else {
            return new ModelAndView("order").addObject("order", order);
        }
    }


    //Delete Item
    @PostMapping("delete/order/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ModelAndView("redirect:/orders/");
    }

}
