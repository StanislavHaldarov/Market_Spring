package com.market.entity.productTypes;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("drinks")
public class Drink extends Product {
    @Min(0)
    private Double volume;
    public Drink() {
    }

    public Drink(String name
            , LocalDate expiredDate, String description, Integer availableQuantity, Double priceBGN, String imageUrl, Double volume) {
        super(name, expiredDate, description, availableQuantity, priceBGN, imageUrl);
        this.volume = volume;
    }

    public String getType() {
        return "drink";
    }


    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
