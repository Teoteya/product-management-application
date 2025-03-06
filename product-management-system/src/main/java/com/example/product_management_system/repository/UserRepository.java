package com.example.product_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product_management_system.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
   // boolean existsByEmail(String email);
}