package com.example.product_management_system.dto;

import com.example.product_management_system.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDto {

    @NotBlank
    String password;

    @NotBlank
    String username;

    @NotBlank
    Set<Role> roles;

}