package com.arthurbonow.loginapi.rest;

import com.arthurbonow.loginapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Este arquivo define a classe PublicController que é um controlador REST no Spring Boot. Este controlador lida com
// solicitações HTTP que são acessíveis publicamente, ou seja, não requerem autenticação.

// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
@RequiredArgsConstructor
// A anotação @RestController indica que esta classe é um controlador REST.
@RestController
// A anotação @RequestMapping define o caminho base para as rotas neste controlador.
@RequestMapping("/public")
public class PublicController {

    // Injeção de dependências.
    private final UserService userService;

    // Método para obter o número de usuários. Retorna um Integer que representa o número de usuários.
    @GetMapping("/numberOfUsers")
    public Integer getNumberOfUsers() {
        // O método getUsers() do userService é chamado para obter todos os usuários.
        // O tamanho da lista de usuários é retornado, que é o número de usuários.
        return userService.getUsers().size();
    }
}