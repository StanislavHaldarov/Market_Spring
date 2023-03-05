package com.market.repository;

import com.market.entity.productTypes.Cosmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {
}
