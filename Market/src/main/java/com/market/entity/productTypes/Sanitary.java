package com.market.entity.productTypes;

import com.market.entity.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.text.Document;
import javax.validation.constraints.Min;

@Entity
public class Sanitary extends BaseEntity {
    @Min(1)
    private Integer count;

    private Double weight;
    private Double volume;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Cascade(CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public Sanitary(Integer count, Double weight, Double volume, Product product) {
        this.count = count;
        this.weight = weight;
        this.volume = volume;
        this.product = product;
    }

    public Sanitary() {
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}