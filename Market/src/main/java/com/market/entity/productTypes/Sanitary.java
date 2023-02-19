package com.market.entity.productTypes;

import com.market.entity.Product;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sanitary extends Product {
    @Column(nullable = false)
    private Integer count;

    public Sanitary() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
