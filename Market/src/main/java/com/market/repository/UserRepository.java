package com.market.repository;

import com.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query("SELECT e FROM User e WHERE e.email = :email")
    User getUserByEmail(@Param("email") String email);
}
