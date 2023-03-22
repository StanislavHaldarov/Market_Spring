package com.market.service.order;

import com.market.dto.request.ProductItem;
import com.market.entity.order.Order;
import com.market.entity.User;
import com.market.util.enums.OrderStatusEnum;

import java.util.List;

public interface OrderService {
    void saveOrderItem(User user, ProductItem productItem);

    void deleteOrderById(Long id);

    void deleteOrderItemById(Long id);

    void submitOrder(Long orderId);

    Order findActiveOrderByUserIdAndStatus(Long id, OrderStatusEnum statusEnum);

    Order findOrderById(Long orderId);

    List<Order> getAllOrders();

    Order sendOrder(Long orderId);

    Order completeOrder(Long orderId);

    List<Order> sortOrdersByDate(List<Order> orders, String sort);

    List<Order> getOrdersByStatuses(OrderStatusEnum[] statuses);


}
