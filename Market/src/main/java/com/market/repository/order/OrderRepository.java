package com.market.repository.order;

import com.market.entity.order.Order;
import com.market.utility.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN o.user u WHERE u.id =:id AND o.status =:status")
    Order findOrderByUserIdAndStatus(@PathVariable(name="id") Long id, @PathVariable(name="status")OrderStatusEnum status);


    @Query("SELECT o FROM Order o JOIN o.orderItemList i WHERE i.id =:id")
    Order findOrderByItemId(@PathVariable(name="id") Long id);


    List<Order> findByStatusIn(List<OrderStatusEnum> asList);
}
