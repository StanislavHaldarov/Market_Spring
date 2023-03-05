package com.market.entity.productTypes;

import com.market.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product extends BaseEntity {
    @NotNull
    @Length(min = 3)
    private String name;

    @Column(nullable = true, name="expired_date", columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expiredDate;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String description;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @NotNull
    @Min(0)
    private Double priceBGN;

    @NotNull
    private String imageUrl;


    public Product(String name, LocalDate expiredDate, String description, Integer availableQuantity, Double priceBGN, String imageUrl) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.description = description;
        this.availableQuantity = availableQuantity;
        this.priceBGN = priceBGN;
        this.imageUrl = imageUrl;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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