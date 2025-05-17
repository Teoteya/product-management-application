package com.example.product_management_system.config;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.product_management_system.security.JwtAuthenticationFilter;

@Configuration
@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
@Schema(description = "Настройка фильтров, ролей и прав доступа")
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Schema(description = "Основной метод конфигурации безопасности")
    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключение CSRF для упрощения работы
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/login", "/register", "/error", "/templates/**", "/static/**", "/css/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Открытые маршруты
                        .requestMatchers("/products/admin/**").hasAuthority("ROLE_ADMIN")    // мы не можем здесь использовать hasRole("ADMIN") т.к. в БД у нас роли начинаются с преффикса "ROLE_", а должны для hasRole начинаться без него, сразу ADMIN и USER
                        .requestMatchers("/categories/admin/**").hasAuthority("ROLE_ADMIN")  // поэтому мы используем hasAuthority, с ним можно использовать преффикс "ROLE_"
                        .anyRequest().authenticated() // Остальные маршруты требуют аутентификации
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Ваш JWT фильтр
                .formLogin(form -> form
                        .defaultSuccessUrl("/products/list", true) // Успешный редирект
                        .permitAll() // Доступен всем
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .permitAll() // Доступен всем
                )
                .httpBasic(); // Включение базовой аутентификации (если требуется)

        return http.build();
    }

    @Schema(description = "Настройка менеджера аутентификации")
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Schema(description = "Настройка шифрования паролей. Использование BCrypt для кодирования паролей")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}