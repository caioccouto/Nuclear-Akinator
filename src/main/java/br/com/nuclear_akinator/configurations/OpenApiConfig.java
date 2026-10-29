package br.com.nuclear_akinator.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Nuclear Akinator API",
                version = "1.0",
                description = "API para gerenciamento de objetos e suas características."
        )
)
public class OpenApiConfig {
}
