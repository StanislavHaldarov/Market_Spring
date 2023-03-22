package com.market.controller;

import com.market.entity.User;
import com.market.entity.order.Order;
import com.market.service.order.OrderService;
import com.market.service.user.UserService;
import com.market.util.enums.OrderStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;


    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


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


    //Delete Order
    @PostMapping("delete/order/{id}")
    public ModelAndView deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ModelAndView("redirect:/orders/");
    }
    @PostMapping("delete/completed-order/{orderId}")
    public ModelAndView deleteCompletedOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrderById(id);
        return new ModelAndView("redirect:/orders/order-management");
    }


    @GetMapping("/order-management")
    public String getAllOrders(@RequestParam(name = "sort",
            required = false) String sort,
                               @RequestParam(name = "status", required = false)
                               OrderStatusEnum[] statuses, Model model) {
        List<Order> orders;
        if (statuses != null && statuses.length > 0) {
            orders = orderService.getOrdersByStatuses(statuses);
        } else {
            orders = orderService.getAllOrders();
        }
        if (orders != null && sort != null) {
            orders = orderService.sortOrdersByDate(orders, sort);
        }
        model.addAttribute("orders", orders);
        return "ordersmanagement";
    }

    @GetMapping("/order-details/{orderId}")
    public String orderDetails(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "order-details";
    }

    @PostMapping("/send/{orderId}")
    public ModelAndView sendOrder(@PathVariable("orderId") Long orderId) {
        orderService.sendOrder(orderId);
        return new ModelAndView("redirect:/orders/order-details/{orderId}");
    }

    @PostMapping("/complete/{orderId}")
    public ModelAndView completeOrder(@PathVariable("orderId") Long orderId) {
        orderService.completeOrder(orderId);
        return new ModelAndView("redirect:/orders/order-details/{orderId}");
    }

}
