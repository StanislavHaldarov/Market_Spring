package com.market.service.impl.order;

import com.market.entity.order.OrderItem;
import com.market.entity.productTypes.Product;
import com.market.repository.order.OrderItemRepository;
import com.market.utility.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceImplTest {
    @InjectMocks
    private OrderItemServiceImp serviceToTest;
    @Mock
    private OrderItemRepository mockOrderItemRepository;

    @Test
    void findByIdTestWhenOrderItemNotFound() {
        when(mockOrderItemRepository.findById(0L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> serviceToTest.findById(0L));

    }

    @Test
    void findByIdTestWhenOrderItemFound() {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        orderItem.setProduct(product);
        orderItem.setQuantity(5);

        when(mockOrderItemRepository.findById(1L)).thenReturn(Optional.of(orderItem));
        assertEquals(serviceToTest.findById(1L), orderItem);
    }
}
