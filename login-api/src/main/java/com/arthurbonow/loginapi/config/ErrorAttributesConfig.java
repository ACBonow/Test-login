package com.arthurbonow.loginapi.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
//Este arquivo é uma classe de configuração do Spring Boot que personaliza os atributos de erro retornados quando
// ocorre uma exceção na aplicação. Ele é útil para controlar quais informações são incluídas na resposta quando
// ocorre um erro.

// A anotação @Configuration indica que esta classe contém definições de beans que serão gerenciados pelo Spring.
@Configuration
public class ErrorAttributesConfig {

    // A anotação @Bean indica que o método produz um bean que será gerenciado pelo Spring.
    @Bean
    public ErrorAttributes errorAttributes() {
        // A classe DefaultErrorAttributes é uma implementação padrão de ErrorAttributes do Spring Boot.
        // Ela fornece os atributos de erro padrão usados ​​pelo Spring Boot.
        return new DefaultErrorAttributes() {
            // Este método é sobrescrito para personalizar os atributos de erro retornados.
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                // Aqui, estamos incluindo os detalhes da exceção, a mensagem de erro e os erros de binding na resposta.
                return super.getErrorAttributes(webRequest, options.including(Include.EXCEPTION, Include.MESSAGE, Include.BINDING_ERRORS));
            }
        };
    }
}