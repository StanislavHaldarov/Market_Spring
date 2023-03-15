package com.market.service.order;

import com.market.entity.order.OrderItem;

public interface OrderItemService {
    void deleteById(Long id);
    OrderItem findById(Long id);

    void saveItem(OrderItem item);
}
