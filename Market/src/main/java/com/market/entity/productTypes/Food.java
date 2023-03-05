package com.market.entity.productTypes;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
@DiscriminatorValue("food")
public class Food extends Product {
    @Column(nullable = true)
    private Double weight;
    public Food() {
    }

    public String getType() {
        return "food";
    }

    public Food(String name, LocalDate expiredDate, String description, Integer availableQuantity, Double priceBGN, String imageUrl, Double weight) {
        super(name, expiredDate, description, availableQuantity, priceBGN, imageUrl);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
