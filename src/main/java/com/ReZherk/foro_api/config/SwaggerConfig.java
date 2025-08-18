package com.ReZherk.foro_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI foroOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Foro API").description("API REST de foro con JWT").version("v1"))
                .externalDocs(new ExternalDocumentation().description("Documentación Swagger UI").url("/swagger-ui.html"));
    }
}
