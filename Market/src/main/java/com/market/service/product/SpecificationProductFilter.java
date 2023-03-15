package com.market.service.product;

import com.market.dto.request.Filter;
import com.market.entity.productTypes.Product;
import com.market.utility.enums.ProductTypeEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SpecificationProductFilter {
    public Specification<Product> filter(Filter filter) {
        return filterByCategories(filter)
                .and(inPriceRange(filter.getMinPrice(), filter.getMaxPrice()))
                .and(withName(filter.getNameKeyword()))
                .and(withQuantity(filter.getQuantityKeyword()));

    }

    private Specification<Product> filterByCategories(Filter filter) {
        return Specification.where(withFoodType(filter.getIsCategoryFoodChecked())
                .or(withDrinksType(filter.getIsCategoryDrinksChecked()))
                .or(withCosmeticsType(filter.getIsCategoryCosmeticsChecked()))
                .or(withSanitaryType(filter.getIsCategorySanitaryChecked())));
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
            return builder.equal(root.get("type"), ProductTypeEnum.DRINKS);
        };

    }

    private Specification<Product> withCosmeticsType(String isCosmeticsChecked) {

        return (root, query, builder) -> {
            if (isCosmeticsChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.COSMETICS);
        };

    }

    private Specification<Product> withSanitaryType(String isSanitaryChecked) {

        return (root, query, builder) -> {
            if (isSanitaryChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.SANITARY);
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

    private Specification<Product> withName(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return null;
            }
            return builder.like(root.get("name"), "%" + name + " %");
        };
    }

    private Specification<Product> withQuantity(Integer quantity){
        return (root, query, builder) -> {
            if(quantity == null){
                return null;
            }
            return builder.equal(root.get("availableQuantity"),quantity);
        };
    }
}
