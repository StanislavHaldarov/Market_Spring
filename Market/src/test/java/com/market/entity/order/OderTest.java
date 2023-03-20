package com.market.entity.order;

import com.market.entity.productTypes.Product;
import com.market.utility.enums.ProductTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OderTest {

    private Order order;

    @BeforeEach
    public void initOrder(){
        Product product = new Product(2L, "Кока кола", ProductTypeEnum.DRINKS, "",
            LocalDate.now(), 15, 1.5, "");
        OrderItem item = new OrderItem(product,10);
        this.order = new Order();
        this.order.getOrderItemList().add(item);
    }

    @Test
    public void findTotalPriceTest(){
        assertEquals(order.findTotalPrice(), 15.0);
    }

    @Test
    public void testIfOrderCanBeMadeWhenIsTrue(){
        assertTrue(order.checkIfOrderCanBeMade());
    }
    @Test
    public void testIfOrderCanBeMadeWhenIsFalse(){
        order.getOrderItemList().get(0).setProduct(null);
        assertFalse(order.checkIfOrderCanBeMade());
    }
}
