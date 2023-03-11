package com.market.service;

import com.market.entity.Filter;
import com.market.entity.productTypes.Product;
import com.market.entity.productTypes.ProductTypeEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SpecificationProductFilter {
    public Specification<Product> filter(Filter filter) {
        return Specification.where(withFoodType(filter.getIsCategoryFoodChecked())
                .and(withDrinksType(filter.getIsCategoryDrinksChecked())
                        .and(withCosmeticsType(filter.getIsCategoryCosmeticsChecked()))
                        .and(withSanitaryType(filter.getIsCategorySanitaryChecked()))
                        .and(inPriceRange(filter.getMinPrice(), filter.getMaxPrice()))
                        .and(withName(filter.getNameKeyword()))
                ));

    }

    private Specification<Product> withFoodType(String isFoodChecked) {

        return (root, query, builder) -> {
            if (isFoodChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.FOOD);
        };

    }

    private Specification<Product> withDrinksType(String isDrinksChecked) {

        return (root, query, builder) -> {
            if (isDrinksChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), "DRINKS");
        };

    }

    private Specification<Product> withCosmeticsType(String isCosmeticsChecked) {

        return (root, query, builder) -> {
            if (isCosmeticsChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), "COSMETICS");
        };

    }

    private Specification<Product> withSanitaryType(String isSanitaryChecked) {

        return (root, query, builder) -> {
            if (isSanitaryChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), "SANITARY");
        };

    }

    private Specification<Product> inPriceRange(Integer min, Integer max) {

        return (root, query, builder) -> {
            if (min == null || max == null) {
                return null;
            }
            return builder.and(builder.lessThanOrEqualTo(root.get("priceBGN"), max),
                    builder.greaterThanOrEqualTo(root.get("priceBGN"), min));
        };

    }

    private Specification<Product> withName(String name){
        return(root, query, builder) -> {
            if (name == null) {
                return null;
            }
            return builder.like(root.get("name"), "%" + name + " %");
        };
    }
}
