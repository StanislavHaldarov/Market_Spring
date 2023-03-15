package com.market.entity.order;

import com.market.entity.BaseEntity;
import com.market.entity.productTypes.Product;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem extends BaseEntity {
    @ManyToOne
    private Product product;

    private Integer quantity;

}
