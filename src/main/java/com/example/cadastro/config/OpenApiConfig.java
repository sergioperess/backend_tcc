package com.example.cadastro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    /**
     * Configuração personalizada para o OpenAPI.
     * @return Instância do OpenAPI configurada.
     */
    OpenAPI customOpenAPI() {
        final String apiTitle = "Criação de usuários";
        final String apiVersion = "v1";
        final String apiDescription = "API para a manipulação de usuários";
        final String termsOfServiceUrl = "http://springcourse.com.br/swagger";
        final String licenseName = "Apache 2.0";
        final String licenseUrl = "http://springcourse.com.br/swagger";

        return new OpenAPI()
                .info(new Info()
                        .title(apiTitle)
                        .version(apiVersion)
                        .description(apiDescription)
                        .termsOfService(termsOfServiceUrl)
                        .license(new License().name(licenseName).url(licenseUrl)));
    }
}

