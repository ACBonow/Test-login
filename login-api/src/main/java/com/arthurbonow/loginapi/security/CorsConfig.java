package com.arthurbonow.loginapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
//Este arquivo define a classe CorsConfig que é uma classe de configuração do Spring Boot. Esta classe é usada para
// configurar o Cross-Origin Resource Sharing (CORS) para a aplicação.

// A anotação @Configuration indica que esta classe é uma classe de configuração do Spring.
@Configuration
public class CorsConfig {

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    // Este bean é uma instância de CorsConfigurationSource que o Spring Security usa para configurar o CORS.
    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origins}") List<String> allowedOrigins) {
        // Cria uma nova instância de CorsConfiguration.
        CorsConfiguration configuration = new CorsConfiguration();
        // Configura a política de CORS para permitir credenciais.
        configuration.setAllowCredentials(true);
        // Configura a política de CORS para permitir solicitações de origens específicas.
        configuration.setAllowedOrigins(allowedOrigins);
        // Configura a política de CORS para permitir todos os métodos HTTP.
        configuration.addAllowedMethod("*");
        // Configura a política de CORS para permitir todos os cabeçalhos.
        configuration.addAllowedHeader("*");
        // Cria uma nova instância de UrlBasedCorsConfigurationSource.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Registra a configuração de CORS para todas as rotas.
        source.registerCorsConfiguration("/**", configuration);
        // Retorna a fonte de configuração de CORS.
        return source;
    }
}