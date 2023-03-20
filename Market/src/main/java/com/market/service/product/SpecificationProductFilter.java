package com.market.service.product;

import com.market.dto.request.Filter;
import com.market.entity.productTypes.Product;
import com.market.utility.enums.ProductTypeEnum;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SpecificationProductFilter {
    public Specification<Product> filter(Filter filter) {
        return filterByCategories(filter)
                .and(inPriceRange(filter.getMinPrice(), filter.getMaxPrice()))
                .and(withName(filter.getNameKeyword()))
                .and(withQuantity(filter.getQuantityKeyword()));

    }

    public Specification<Product> filterByCategories(Filter filter) {
        return Specification.where(withFoodType(filter.getIsCategoryFoodChecked())
                .or(withDrinksType(filter.getIsCategoryDrinksChecked()))
                .or(withCosmeticsType(filter.getIsCategoryCosmeticsChecked()))
                .or(withSanitaryType(filter.getIsCategorySanitaryChecked())));
    }

    public Specification<Product> withFoodType(String isFoodChecked) {

        return (root, query, builder) -> {
            if (isFoodChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.FOOD);
        };

    }

    public Specification<Product> withDrinksType(String isDrinksChecked) {

        return (root, query, builder) -> {
            if (isDrinksChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.DRINKS);
        };

    }

    public Specification<Product> withCosmeticsType(String isCosmeticsChecked) {

        return (root, query, builder) -> {
            if (isCosmeticsChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.COSMETICS);
        };

    }

    public Specification<Product> withSanitaryType(String isSanitaryChecked) {

        return (root, query, builder) -> {
            if (isSanitaryChecked == null) {
                return null;
            }
            return builder.equal(root.get("type"), ProductTypeEnum.SANITARY);
        };

    }

    public Specification<Product> inPriceRange(Integer min, Integer max) {

        return (root, query, builder) -> {
            if (min == null || max == null) {
                return null;
            }
            return builder.and(builder.lessThanOrEqualTo(root.get("priceBGN"), max),
                    builder.greaterThanOrEqualTo(root.get("priceBGN"), min));
        };

    }

    public Specification<Product> withName(String name) {
        return (root, query, builder) -> {
            if (name == null) {
                return null;
            }
            return builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public Specification<Product> withQuantity(Integer quantity) {
        return (root, query, builder) -> {
            if (quantity == null) {
                return null;
            }
            return builder.equal(root.get("availableQuantity"), quantity);
        };
    }

    public Specification<Product> withAvailableQuantity() {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("availableQuantity"), 1);
    }

    public Specification<Product> withNotExpiredDate() {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("expiredDate"), LocalDate.now());
    }

}
