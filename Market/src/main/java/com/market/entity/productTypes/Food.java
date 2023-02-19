package com.market.entity.productTypes;

import com.market.entity.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="food")
public class Food extends Product {
    @Column(nullable = false, name="expired_date", columnDefinition = "DATE")
    private Date expiredDate;
    @Column(nullable = false)
    private Double weight;
    public Food() {
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
