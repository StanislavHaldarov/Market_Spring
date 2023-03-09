package com.market.repository;

import com.market.entity.productTypes.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f JOIN f.product p WHERE p.availableQuantity >= 1")
    List<Food> findAllAvailable();

    @Query("SELECT f FROM Food f JOIN f.product p WHERE p.id =:id")
    Food findFoodByProductId(@PathVariable(name="id") Long id);

}
