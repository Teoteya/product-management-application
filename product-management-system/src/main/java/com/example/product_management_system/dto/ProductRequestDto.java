package com.example.product_management_system.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    @NotNull
    String name;

    String description;

    @Min(0)
    Double price;

    MultipartFile image;

    @NotNull
    String categoryName;

    @NotNull
    String status;
}
