package com.market.repository.product;

import com.market.entity.productTypes.Drink;
import com.market.entity.productTypes.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    @Query("SELECT d FROM Drink d JOIN d.product p WHERE p.id =:id")
    Drink findDrinkByProductId(@PathVariable(name="id") Long id);
}
