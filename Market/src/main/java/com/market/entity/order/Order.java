package com.market.entity.order;

import com.market.entity.BaseEntity;
import com.market.entity.User;
import com.market.util.enums.OrderStatusEnum;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @CreationTimestamp
    private LocalDate date;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    public Double findTotalPrice() {
        Double total = 0.0;
        for (OrderItem item : orderItemList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getPriceBGN() * item.getQuantity();
            }
        }
        return total;
    }

    public boolean checkIfOrderCanBeMade() {

        return (
                this.orderItemList.stream().filter(orderItem -> orderItem.getProduct() == null).toList().size() == 0
                        && this.orderItemList.stream().filter(orderItem -> orderItem.getProduct().getAvailableQuantity() < orderItem.getQuantity()).toList().size() == 0);
    }

    public Order() {
        this.orderItemList = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}


