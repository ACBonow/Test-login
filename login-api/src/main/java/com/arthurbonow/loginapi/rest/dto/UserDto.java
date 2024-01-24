package com.arthurbonow.loginapi.rest.dto;
//Este arquivo define a classe de registro UserDto em Java. As classes de registro são uma nova adição ao Java 16 e
// são uma maneira concisa de criar classes de dados imutáveis. Neste caso, UserDto é usada para transferir dados do
// usuário entre diferentes camadas da aplicação.

// A palavra-chave 'record' é usada para definir uma classe de registro em Java.
// 'UserDto' é o nome da classe de registro.
// A lista de parâmetros entre parênteses define os campos da classe de registro.
// Cada campo é automaticamente final e privado, e um getter é gerado para cada campo.
// Neste caso, 'UserDto' tem seis campos: 'id', 'email', 'password', 'name', 'cpf' e 'role'.
public record UserDto(java.util.UUID id, String email, String password, String name, String cpf, String role) {
}