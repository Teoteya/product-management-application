package com.example.product_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Запрос для аутентификации пользователя. Класс используется для передачи данных аутентификации (email и пароль) от клиента к серверу")
public class AuthRequestDto {

    @Schema(description = "Username пользователя", required = true)
    @NotBlank(message = "Username обязателен")
    String username;

    @Schema(description = "Пароль пользователя", example = "password123", required = true)
    @NotBlank(message = "Пароль обязателен")
    String password;

}