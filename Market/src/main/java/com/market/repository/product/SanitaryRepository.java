package com.market.repository.product;

import com.market.entity.productTypes.Sanitary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface SanitaryRepository extends JpaRepository<Sanitary, Long> {
    @Query("SELECT s FROM Sanitary s JOIN s.product p WHERE p.availableQuantity >= 1")
    List<Sanitary> findAllAvailable();

    @Query("SELECT s FROM Sanitary s JOIN s.product p WHERE p.id =:id")
    Sanitary findSanitaryByProductId(@PathVariable(name = "id") Long id);
}
