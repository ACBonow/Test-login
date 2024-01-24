package com.arthurbonow.loginapi.rest.dto;

import java.util.UUID;
//Este arquivo define uma classe de registro AuthResponse em Java. As classes de registro são uma nova adição ao
// Java 16 e são uma maneira concisa de criar classes de dados imutáveis. Neste caso, AuthResponse é usada para
// representar a resposta de autenticação que será enviada ao cliente após uma autenticação bem-sucedida.


// A palavra-chave 'record' é usada para definir uma classe de registro em Java.
// 'AuthResponse' é o nome da classe de registro.
// A lista de parâmetros entre parênteses define os campos da classe de registro.
// Cada campo é automaticamente final e privado, e um getter é gerado para cada campo.
// Neste caso, 'AuthResponse' tem três campos: 'id', 'name' e 'role'.
public record AuthResponse(UUID id, String name, String role) {
}