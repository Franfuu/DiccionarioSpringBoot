package com.github.franfuu.springdiccionario.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Diccionario API",
                version = "1.0",
                description = "API para la gestión de palabras y definiciones"
        )
)
public class OpenApiConfig {
}
