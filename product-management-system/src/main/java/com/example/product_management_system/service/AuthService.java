package com.example.product_management_system.service;

import com.example.product_management_system.dto.AuthRequestDto;
import com.example.product_management_system.dto.AuthResponseDto;
import com.example.product_management_system.dto.RegisterRequestDto;
import com.example.product_management_system.entity.User;
import com.example.product_management_system.exception.CustomException;
import com.example.product_management_system.repository.UserRepository;
import com.example.product_management_system.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.product_management_system.repository.UserRepository;
@Service
@Schema(description = "Сервис для аутентификации, регистрации и генерации JWT токенов.")
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Schema(description = "Позволяет зарегистрировать нового пользователя")
    public void register(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException("User with this email already exists", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        userRepository.save(user);
    }

    @Schema(description = "Возвращает JWT токен для аутентифицированного пользователя")
    public AuthResponseDto login(AuthRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException("Invalid email or password", HttpStatus.UNAUTHORIZED));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRoles().toString());
        return new AuthResponseDto(token);
    }
}