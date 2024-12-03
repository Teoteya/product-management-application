package com.example.product_management_system.service;

import com.example.product_management_system.exception.CustomException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.product_management_system.entity.Product;
import com.example.product_management_system.dto.CategoryRequestDto;
import com.example.product_management_system.dto.CategoryResponseDto;
import com.example.product_management_system.repository.CategoryRepository;
import com.example.product_management_system.entity.Category;
import com.example.product_management_system.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Schema(description = "Сервис для управления категориями")
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Schema(description = "Создание категории")
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    @Schema(description = "Просмотр списка категорий")
    public Page<CategoryResponseDto> getCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(this::mapToResponse);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Schema(description = "Редактирование категории")
    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto request) {
        Category category = categoryRepository.
                findById(id)
                .orElseThrow(() -> new CustomException("Category not found", HttpStatus.NOT_FOUND));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return mapToResponse(updatedCategory);
    }

    @Schema(description = "Удаление категории")
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        Optional<Category> defaultCategoryOpt = categoryRepository.findById(1L);
        if (defaultCategoryOpt.isEmpty()) {
            throw new CustomException("Default category not found", HttpStatus.NOT_FOUND);
        }
        Category defaultCategory = defaultCategoryOpt.get();
        // Найти все продукты, у которых текущая категория — та, которую нужно удалить
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getCategory() != null && product.getCategory().getId().equals(id)) {
                product.setCategory(defaultCategory);
                product.setStatus("не активен");
                productRepository.save(product); // Сохранение изменений
            }
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDto mapToResponse(Category category) {
        CategoryResponseDto response = new CategoryResponseDto();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        return response;
    }

    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category not found", HttpStatus.NOT_FOUND));
        return mapToResponse(category);
    }
}