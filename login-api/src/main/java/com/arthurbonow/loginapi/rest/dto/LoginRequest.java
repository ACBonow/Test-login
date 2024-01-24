package com.arthurbonow.loginapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
//Este arquivo define a classe LoginRequest que é usada para representar os dados de uma solicitação de login. Esta
// classe é usada quando um cliente envia uma solicitação de login para a API.

// A anotação @Data do Lombok gera automaticamente getters, setters, equals, hashCode e toString para os campos da classe.
@Data
public class LoginRequest {

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    @Schema(example = "user@example.com")
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @NotBlank
    private String email;

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    @Schema(example = "password")
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @NotBlank
    private String password;
}