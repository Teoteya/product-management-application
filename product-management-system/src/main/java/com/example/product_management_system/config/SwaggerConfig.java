package com.example.product_management_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Приложение. Система управления продуктами")
                        .version("1.0")
                        .description("Документация для API. API предоставляет доступ к управлению продуктами и категориями")
                        .contact(new Contact()
                                .name("Команда разработки приложения")
                                .email("teyarad@bk.ru")
                        ));
    }
}

