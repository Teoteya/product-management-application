package com.example.product_management_system.controller;

import com.example.product_management_system.dto.ProductRequestDto;
import com.example.product_management_system.dto.ProductResponseDto;
import com.example.product_management_system.entity.Category;
import com.example.product_management_system.exception.CustomException;
import com.example.product_management_system.repository.CategoryRepository;
import com.example.product_management_system.repository.ProductRepository;
import com.example.product_management_system.service.CategoryService;
import com.example.product_management_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/products")
@Tag(name = "Product API", description = "Управление продуктами")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Operation(summary = "Просмотреть сохраненный продукт", description = "Страница для отображения созданного продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/created")
    public String createProduct(@Valid @ModelAttribute ProductRequestDto request,
                                @RequestParam("image") MultipartFile image,
                                Model model) {
        try {
            // Конвертируем MultipartFile в byte[]
            byte[] imageBytes = image != null && !image.isEmpty() ? image.getBytes() : null;
            // Создаем продукт с полученным изображением
            ProductResponseDto product = productService.createProduct(request, imageBytes);
            model.addAttribute("product", product);
            return "productCreated";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error while processing the image: " + e.getMessage());
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unexpected error occurred: " + e.getMessage());
            return "error";
        }
    }

    @Operation(summary = "Создать продукт", description = "Форма для создания нового продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/create")
    public String showCreateProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("productRequest", new ProductRequestDto());
        return "createProduct";
    }

    @Operation(summary = "Получить список продуктов", description = "Возвращает список всех продуктов с фильтрацией")
    @GetMapping("/list")
    public String getProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo,
            Model model) {
        try {
            Page<ProductResponseDto> products = productService.getProducts(page, size, categoryId, name, priceFrom, priceTo);
            model.addAttribute("products", products.getContent());
            return "productList";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Страница ошибки
        }
    }

    @Operation(summary = "Просмотреть обновленный продукт", description = "Страница для отображения обновленного продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/{id}/update")
    public String updateProduct(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("status") String status,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Model model) {

        log.info("Received update request for product with ID: {}", id);
        log.info("Name: {}, Description: {}, Price: {}", name, description, price);

        ProductRequestDto request = new ProductRequestDto();
        request.setName(name);
        request.setDescription(description);
        request.setPrice(price);
        request.setCategoryName(categoryName);
        request.setStatus(status);
        request.setImage(image); // Устанавливаем файл в DTO

        ProductResponseDto product = productService.updateProduct(id, request);
        model.addAttribute("product", product);

        log.info("Product update process completed.");
        return "productUpdated";
    }

    @Operation(summary = "Обновить продукт", description = "Форма для обновления существующего продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/{id}/edit")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @Operation(summary = "Просмотреть сообщение об удаленном продукте", description = "Страница для отображения сообщения об успешном удалении продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }

    @Operation(summary = "Удалить продукт", description = "Форма для удаления продукта")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/{id}/delete")
    public String showDeleteProductForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @GetMapping("/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.getProductById(id);

        byte[] image = productResponseDto.getImage();

        if (image == null) {
            throw new CustomException("Image not found", HttpStatus.NOT_FOUND);
        }

        System.out.println("Image found for product ID: " + id);

        // Указываем MIME-тип для PNG
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }


}