package com.market.entity.productTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="cosmetics")
public class Cosmetic extends Product {
    @Column(nullable = false, name="expired_date", columnDefinition = "DATE")
    private Date expiredDate;
    @Column(nullable = false)
    private Double volume;

    public Cosmetic() {
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}