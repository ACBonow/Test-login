package com.arthurbonow.loginapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Este arquivo é uma classe de configuração do Spring Boot que configura o Swagger, uma ferramenta para documentação de
// APIs. Ele define como o Swagger deve gerar a documentação da API, incluindo informações sobre a aplicação e esquemas de segurança.

// A anotação @Configuration indica que esta classe contém definições de beans que serão gerenciados pelo Spring.
@Configuration
public class SwaggerConfig {

    // A anotação @Value injeta o valor da propriedade spring.application.name do arquivo de propriedades do Spring Boot.
    @Value("${spring.application.name}")
    private String applicationName;

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    // Este bean é uma instância de OpenAPI que o Swagger usa para gerar a documentação da API.
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Define os componentes da documentação da API, incluindo esquemas de segurança.
                .components(
                        new Components().addSecuritySchemes(BASIC_AUTH_SECURITY_SCHEME,
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                // Define as informações sobre a API.
                .info(new Info().title(applicationName));
    }

    // Constante que define o nome do esquema de segurança para autenticação básica.
    public static final String BASIC_AUTH_SECURITY_SCHEME = "basicAuth";
}