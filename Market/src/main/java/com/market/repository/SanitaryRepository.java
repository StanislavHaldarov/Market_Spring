package com.market.repository;

import com.market.entity.productTypes.Sanitary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanitaryRepository extends JpaRepository<Sanitary, Long> {
}
