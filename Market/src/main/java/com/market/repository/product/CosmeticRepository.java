package com.market.repository.product;

import com.market.entity.productTypes.Cosmetic;
import com.market.entity.productTypes.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {

    @Query("SELECT c FROM Cosmetic c JOIN c.product p WHERE p.id =:id")
    Cosmetic findCosmeticByProductId(@PathVariable(name="id") Long id);
}
