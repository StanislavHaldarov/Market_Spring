package com.market.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductItem {

    Long id;
    private String name;
    private String description;
    private Double priceBGN;
    private String imageUrl;
    @NotBlank
    @Min(1)
    private Integer quantity;

    public ProductItem() {
    }

    public ProductItem(Long id, String name, String description, Double priceBGN, String imageUrl, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceBGN = priceBGN;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

