package com.example.product_management_system.controller;

import com.example.product_management_system.dto.CategoryRequestDto;
import com.example.product_management_system.dto.CategoryResponseDto;
import com.example.product_management_system.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@Tag(name = "Category API", description = "Управление категориями")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Просмотреть созданную категорию", description = "Страница для отображения созданной категории")
    @PostMapping("/created")
    public String createCategory(@Valid @ModelAttribute CategoryRequestDto request, Model model) {
        CategoryResponseDto category = categoryService.createCategory(request);
        model.addAttribute("category", category);
        return "categoryCreated";
    }

    @Operation(summary = "Создать категорию", description = "Форма для создания категории")
    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("categoryRequest", new CategoryRequestDto());
        return "createCategory";
    }

    @Operation(summary = "Получить список категорий", description = "Возвращает список всех категорий")
    @GetMapping("/list")
    public String getCategories(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<CategoryResponseDto> categories = categoryService.getCategories(pageable);
        model.addAttribute("categories", categories.getContent());
        model.addAttribute("currentPage", categories.getNumber());
        model.addAttribute("totalPages", categories.getTotalPages());
        return "categoryList";
    }


    @Operation(summary = "Просмотреть обновленную категорию", description = "Страница для отображения обновленной категории")
    @PutMapping("/{id}/update")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute CategoryRequestDto request, Model model) {
        CategoryResponseDto category = categoryService.updateCategory(id, request);
        model.addAttribute("category", category);
        return "categoryUpdated";
    }

    @Operation(summary = "Обновить категорию", description = "Форма для обновления существующей категории")
    @GetMapping("/{id}/edit")
    public String showUpdateCategoryForm(@PathVariable Long id, Model model) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "updateCategory";
    }

    @Operation(summary = "Просмотреть сообщение об удаленной категории", description = "Страница для отображения сообщения об успешном удалении категории")
    @DeleteMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/list";
    }

    @Operation(summary = "Удалить категорию", description = "Форма для удаления категории")
    @GetMapping("/{id}/delete")
    public String showDeleteCategoryForm(@PathVariable Long id, Model model) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "deleteCategory";
    }
}