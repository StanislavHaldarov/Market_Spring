package com.market.service.impl.order;

import com.market.dto.request.ProductItem;
import com.market.entity.Role;
import com.market.entity.User;
import com.market.entity.order.Order;
import com.market.entity.order.OrderItem;
import com.market.entity.productTypes.Product;
import com.market.repository.order.OrderRepository;
import com.market.repository.product.ProductRepository;
import com.market.service.MailService;
import com.market.service.order.InvoiceService;
import com.market.service.order.OrderItemService;
import com.market.service.product.ProductService;
import com.market.utility.enums.OrderStatusEnum;
import com.market.utility.enums.ProductTypeEnum;
import com.market.utility.enums.RoleNameEnum;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl serviceToTest;
    @Mock
    private ProductService mockProductService;
    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private OrderItemService mockOrderItemService;
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private InvoiceService mockInvoiceService;
    @Mock
    private MailService mailService;


    @Test
    public void submitOrderTest() {
        User user = initUser();
        Product product = initProduct();
        OrderItem orderItem = initOrderItem(product);
        Order order = initOrder(user, orderItem);

        when(mockOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        serviceToTest.submitOrder(1L);

        assertEquals(product.getAvailableQuantity(), 45);
        assertEquals(order.getStatus(), OrderStatusEnum.PROCESSING);
        assertEquals(order.getTotalPrice(), 9.0);
        verify(mockProductService, times(1)).saveProduct(product);
        verify(mockOrderRepository, times(1)).save(order);

    }

    private User initUser() {
        User user1 = new User("testUser", "testPassword", new Role(RoleNameEnum.CUSTOMER),
                "Tsvetelina", "Ivanova", "cvetelinaivanova7@gmail.com",
                24, 2000.0, "0886861648", true
        );

        user1.setId(1L);
        return user1;
    }

    private Product initProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Хляб - Добруджа");
        product.setType(ProductTypeEnum.FOOD);
        product.setPriceBGN(1.80);
        product.setAvailableQuantity(50);
        List<OrderItem> items = new ArrayList<>();
        product.setItems(items);
        return product;

    }

    private OrderItem initOrderItem(Product product) {
        OrderItem item = new OrderItem(product, 5);
        item.setId(1L);
        return item;
    }

    private Order initOrder(User user, OrderItem orderItem) {
        Order order = new Order();
        order.setId(1L);
        order.setUser(user);

        List<OrderItem> items = new ArrayList<>();
        items.add(orderItem);

        order.setOrderItemList(items);
        order.setTotalPrice(order.findTotalPrice());

        return order;
    }

    private ProductItem initProductItem(Product product, Integer quantity) {
        ProductItem productItem1 = new ProductItem();
        productItem1.setId(product.getId());
        productItem1.setQuantity(quantity);

        return productItem1;

    }


}

