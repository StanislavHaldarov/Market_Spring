package com.market.entity.order;

import com.market.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
//  TODO: Create User entity and add it as a field here
    @ManyToMany
   private List<OrderItem> orderItemList;

    private LocalDateTime date = LocalDateTime.now();
}
