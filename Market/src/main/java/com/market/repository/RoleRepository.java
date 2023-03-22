package com.market.repository;

import com.market.entity.Role;
import com.market.util.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role findRoleByName(@Param("name")RoleNameEnum name);
    Optional<Role> findByName(RoleNameEnum roleNameEnum);
}
