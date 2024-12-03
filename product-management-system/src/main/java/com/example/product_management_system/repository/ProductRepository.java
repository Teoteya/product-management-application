package com.example.product_management_system.repository;

import com.example.product_management_system.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " + "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:name IS NULL OR p.name = :name) AND " +
            "(:priceFrom IS NULL OR :priceTo IS NULL OR p.price BETWEEN :priceFrom AND :priceTo)")
    Page<Product> findAllWithFilters(Long categoryId, String name, Double priceFrom, Double priceTo, Pageable pageable);

    boolean existsById(Long id);
}
