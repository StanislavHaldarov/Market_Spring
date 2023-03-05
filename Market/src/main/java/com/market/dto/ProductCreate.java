package com.market.dto;

import com.market.utility.validation.ValidateExpiredDate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProductCreate {

    @NotNull
    private String type;
    @Length(min = 3)
    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @Min(0)
    @NotNull
    private Double priceBGN;
    @NotNull
    private String imageUrl;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ValidateExpiredDate
    private LocalDate expiredDate;
    @Min(0)
    private Double weight;
    @Min(0)
    private Double volume;
    @Min(1)
    private Integer count;


    public ProductCreate() {
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Double getPriceBGN() {
        return priceBGN;
    }

    public void setPriceBGN(Double priceBGN) {
        this.priceBGN = priceBGN;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
