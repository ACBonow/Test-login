package com.arthurbonow.loginapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
//Este arquivo define a classe SignUpRequest que é usada para representar os dados de uma solicitação de cadastro.
// Esta classe é usada quando um cliente envia uma solicitação de cadastro para a API.

// A anotação @Data do Lombok gera automaticamente getters, setters, equals, hashCode e toString para os campos da classe.
@Data
public class SignUpRequest {

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    @Schema(example = "user3@mycompany.com")
    // A anotação @Email do Jakarta Validation é usada para validar que este campo é um endereço de email válido.
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @Email
    @NotBlank
    private String email;

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @Schema(example = "User4321")
    @NotBlank
    private String password;

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @Schema(example = "User3")
    @NotBlank
    private String name;

    // A anotação @Schema do Swagger é usada para fornecer um exemplo de valor para este campo na documentação da API.
    // A anotação @NotBlank do Jakarta Validation é usada para indicar que este campo não pode ser nulo nem vazio.
    @Schema(example = "123.456.789-01")
    @NotBlank
    private String cpf;
}