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
@DiscriminatorValue("cosmetic")
public class Cosmetic extends Product {

    @Min(0)
    private Double weight;
    @Min(0)
    private Double volume;

    public Cosmetic() {
    }

    public Cosmetic(String name,
                    LocalDate expiredDate,
                    String description,
                    Integer availableQuantity,
                    Double priceBGN,
                    String imageUrl, Double weight, Double volume) {
        super(name, expiredDate, description, availableQuantity, priceBGN, imageUrl);
        this.weight = weight;
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getType() {
        return "cosmetic";
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}