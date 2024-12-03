package com.example.product_management_system.repository;

import com.example.product_management_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsById(Long id);
    Optional<Category> findByName(String name);
}