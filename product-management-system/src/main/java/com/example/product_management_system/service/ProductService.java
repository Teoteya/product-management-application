package com.example.product_management_system.service;
import com.example.product_management_system.dto.ProductResponseDto;
import com.example.product_management_system.entity.Category;
import com.example.product_management_system.exception.CustomException;
import com.example.product_management_system.repository.CategoryRepository;
import com.example.product_management_system.repository.ProductRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.product_management_system.entity.Product;
import com.example.product_management_system.dto.ProductRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Schema(description = "Сервис для управления продуктами")
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Schema(description = "Создание продукта")
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request, byte[] imageBytes) {
        Category category = categoryRepository.findByName(request.getCategoryName())
                .orElseThrow(() -> new CustomException("Category not found", HttpStatus.NOT_FOUND));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription() != null ? request.getDescription() : " ");
        product.setPrice(request.getPrice());
        product.setImage(imageBytes);
        product.setCategory(category);
        product.setStatus(request.getStatus());
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Schema(description = "Поиск продуктов по категории, наименованию и диапазону цен ОТ/ДO")
    public Page<ProductResponseDto> getProducts(int page, int size, Long categoryId, String name, Double priceFrom, Double priceTo) {
        Page<Product> products = productRepository.findAllWithFilters(categoryId, name, priceFrom, priceTo, PageRequest.of(page, size));
        return products.map(this::mapToResponse);
    }

    @Schema(description = "Редактирование продукта")
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findByName(request.getCategoryName())
                .orElseThrow(() -> new CustomException("Category not found", HttpStatus.NOT_FOUND));

        log.info("Product found, updating details...");

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            try {
                log.info("Processing image...");
                // Проверяем, что изображение в формате PNG
                if (!"image/png".equals(request.getImage().getContentType())) {
                    throw new CustomException("Only PNG images are allowed", HttpStatus.BAD_REQUEST);
                }
                // Сохраняем изображение как byte[]
                product.setImage(request.getImage().getBytes());

            } catch (IOException e) {
                log.error("Ошибка при конвертации изображения в byte[]", e);
                throw new CustomException("Error converting image to byte[]", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        product.setCategory(category);
        product.setStatus(request.getStatus());
        //product.setCreatedAt(LocalDateTime.now()); // Дата создания не меняется

        Product updatedProduct = productRepository.save(product);
        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return mapToResponse(updatedProduct);
    }

    @Schema(description = "Удаление продукта")
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("Product not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
    private ProductResponseDto mapToResponse(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setCategoryName(product.getCategory().getName());
        response.setStatus(product.getStatus());
        response.setCreatedAt(product.getCreatedAt());
        return response;
    }

    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));
        return mapToResponse(product);
    }
}