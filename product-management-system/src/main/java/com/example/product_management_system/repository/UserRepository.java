package com.example.product_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product_management_system.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}