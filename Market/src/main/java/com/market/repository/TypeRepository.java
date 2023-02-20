package com.market.repository;

import com.market.entity.productTypes.ProductTypeEnum;
import com.market.entity.productTypes.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("SELECT t FROM Type t WHERE t.name = :name")
    Type findTypeByName(@Param("name")ProductTypeEnum name);
}
