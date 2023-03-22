package com.market.dto.request;


public class Filter {
    private String isCategoryFoodChecked;
    private String isCategoryDrinksChecked;
    private String isCategoryCosmeticsChecked;
    private String isCategorySanitaryChecked;
    private String isCategoryOtherChecked;

    private String orderBy;

    private Integer minPrice;
    private Integer maxPrice;
    private String nameKeyword;
    private Integer quantityKeyword;


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getIsCategoryOtherChecked() {
        return isCategoryOtherChecked;
    }

    public void setIsCategoryOtherChecked(String isCategoryOtherChecked) {
        this.isCategoryOtherChecked = isCategoryOtherChecked;
    }

    public String getIsCategoryFoodChecked() {
        return isCategoryFoodChecked;
    }


    public void setIsCategoryFoodChecked(String isCategoryFoodChecked) {
        this.isCategoryFoodChecked = isCategoryFoodChecked;
    }

    public String getIsCategoryDrinksChecked() {
        return isCategoryDrinksChecked;
    }

    public void setIsCategoryDrinksChecked(String isCategoryDrinksChecked) {
        this.isCategoryDrinksChecked = isCategoryDrinksChecked;
    }

    public String getIsCategoryCosmeticsChecked() {
        return isCategoryCosmeticsChecked;
    }

    public void setIsCategoryCosmeticsChecked(String isCategoryCosmeticsChecked) {
        this.isCategoryCosmeticsChecked = isCategoryCosmeticsChecked;
    }

    public String getIsCategorySanitaryChecked() {
        return isCategorySanitaryChecked;
    }

    public void setIsCategorySanitaryChecked(String isCategorySanitaryChecked) {
        this.isCategorySanitaryChecked = isCategorySanitaryChecked;
    }


    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public Integer getQuantityKeyword() {
        return quantityKeyword;
    }

    public void setQuantityKeyword(Integer quantityKeyword) {
        this.quantityKeyword = quantityKeyword;
    }

    public Filter() {
    }
}
