package com.market.dto.request;

import com.market.util.enums.ProductTypeEnum;
import com.market.util.validation.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@ValidateFood
@ValidateDrinks
@ValidateCosmetics
@ValidateSanitary
public class ProductCreate {
    Long id;
    @NotNull
    private ProductTypeEnum type;
    @Length(min = 3)
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @Min(0)
    @NotNull
    private Double priceBGN;
    @NotNull
    @NotBlank
    private String imageUrl;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ValidateExpiredDateValue
    private LocalDate expiredDate;
    @Min(0)
    private Double weight;
    @Min(0)
    private Double volume;
    @Min(1)
    private Integer count;

    private String description;

    public ProductCreate(Long id, ProductTypeEnum type, String name, Integer availableQuantity,
                         Double priceBGN, String imageUrl, LocalDate expiredDate,
                         Double weight, Double volume, Integer count, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.priceBGN = priceBGN;
        this.imageUrl = imageUrl;
        this.expiredDate = expiredDate;
        this.weight = weight;
        this.volume = volume;
        this.count = count;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
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
