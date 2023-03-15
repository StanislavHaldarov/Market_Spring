package com.market.service.impl.order;

import com.market.entity.order.OrderItem;
import com.market.repository.order.OrderItemRepository;
import com.market.service.order.OrderItemService;
import com.market.utility.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemServiceImp implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImp(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem findById(Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if(!orderItem.isPresent()){
            throw new NotFoundException("не съществува  такъв item");
        }

        return orderItem.get();
    }

    @Override
    public void saveItem(OrderItem item) {
        orderItemRepository.save(item);
    }
}
