package com.market.entity.productTypes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("sanitary")
public class Sanitary extends Product {
    @Min(1)
    private Integer count;

    public Sanitary() {
    }

    public Sanitary(String name, LocalDate expiredDate, String description, Integer availableQuantity, Double priceBGN, String imageUrl, Integer count) {
        super(name, expiredDate, description, availableQuantity, priceBGN, imageUrl);
        this.count = count;
    }

    public String getType() {
        return "sanitary";
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}