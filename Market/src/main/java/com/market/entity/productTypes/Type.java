package com.market.entity.productTypes;

import com.market.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "types")
public class Type extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum name;

    public Type() {
    }

    public Type(ProductTypeEnum name) {
        this.name = name;
    }

    public ProductTypeEnum getName() {
        return name;
    }

    public void setName(ProductTypeEnum name) {
        this.name = name;
    }
}
