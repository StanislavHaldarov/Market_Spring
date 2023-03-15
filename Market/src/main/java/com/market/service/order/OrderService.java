package com.market.service.order;

import com.market.dto.request.ProductItem;
import com.market.entity.order.Order;
import com.market.entity.User;
import com.market.utility.enums.OrderStatusEnum;

public interface OrderService {
    void saveOrderItem(User user, ProductItem productItem);

    Order findOrderByItemId(Long id);

    void deleteOrderById(Long id);

    //    void deleteOrderItemByIdAndUser(User user, Long id);
    void deleteOrderItemById(Long id);


    void submitOrder(Long orderId);

    Order findActiveOrderByUserIdAndStatus(Long id, OrderStatusEnum statusEnum);

    Order findOrderById(Long orderId);
}
