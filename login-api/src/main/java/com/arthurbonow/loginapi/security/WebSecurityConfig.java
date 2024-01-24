
package com.arthurbonow.loginapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//Este arquivo define a classe WebSecurityConfig que é uma classe de configuração do Spring Security. Esta classe é
// usada para configurar a segurança da aplicação, incluindo a autenticação, autorização, política de sessão,
// configuração CORS, configuração CSRF e configuração de cabeçalhos de segurança.


// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @Configuration indica que esta classe é uma classe de configuração do Spring.
// A anotação @EnableWebSecurity habilita a segurança web do Spring Security.
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    // Este bean é uma instância de AuthenticationManager que o Spring Security usa para realizar a autenticação.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    // Este bean é uma instância de SecurityFilterChain que o Spring Security usa para aplicar a segurança na aplicação.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Configura as autorizações de requisições HTTP.
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // Configura as autorizações para rotas específicas.
                        .requestMatchers(HttpMethod.GET, "/api/users/me").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers("/api/users", "/api/users/**","/api/change-role/**").hasAuthority(ADMIN)
                        .requestMatchers("/public/**", "/auth/**","auth/recoveryPassword/**").permitAll()
                        .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                // Configura a autenticação básica.
                .httpBasic(Customizer.withDefaults())
                // Configura a política de sessão.
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura o CORS.
                .cors(Customizer.withDefaults())
                // Desabilita o CSRF.
                .csrf(AbstractHttpConfigurer::disable)
                // Configura os cabeçalhos de segurança.
                .headers(headers -> headers.frameOptions().sameOrigin())
                // Constrói a cadeia de filtros de segurança.
                .build();
    }

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    // Este bean é uma instância de PasswordEncoder que o Spring Security usa para codificar senhas.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Constantes que representam os papéis de usuário.
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
}