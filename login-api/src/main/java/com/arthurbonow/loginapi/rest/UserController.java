package com.arthurbonow.loginapi.rest;

import com.arthurbonow.loginapi.config.SwaggerConfig;
import com.arthurbonow.loginapi.mapper.UserMapper;
import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.rest.dto.UserDto;
import com.arthurbonow.loginapi.security.CustomUserDetails;
import com.arthurbonow.loginapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//Este arquivo define a classe UserController que é um controlador REST no Spring Boot. Este controlador lida com as
// solicitações HTTP relacionadas à manipulação de usuários, incluindo obtenção de informações do usuário atual,
// listagem de todos os usuários, obtenção de um usuário específico por email, exclusão de um usuário e alteração do
// papel de um usuário.

// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @RestController indica que esta classe é um controlador REST.
// A anotação @RequestMapping define o caminho base para as rotas neste controlador.
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Injeção de dependências.
    private final UserService userService;
    private final UserMapper userMapper;

    // Método para obter o usuário atualmente autenticado.
    // A anotação @Operation do Swagger é usada para fornecer detalhes sobre esta operação na documentação da API.
    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return userMapper.toUserDto(userService.validateAndGetUserByEmail(currentUser.getEmail()));
    }

    // Método para obter todos os usuários.
    // A anotação @Operation do Swagger é usada para fornecer detalhes sobre esta operação na documentação da API.
    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Método para obter um usuário específico por email.
    // A anotação @Operation do Swagger é usada para fornecer detalhes sobre esta operação na documentação da API.
    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userMapper.toUserDto(userService.validateAndGetUserByEmail(email));
    }

    // Método para excluir um usuário.
    // A anotação @Operation do Swagger é usada para fornecer detalhes sobre esta operação na documentação da API.
    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("/{email}")
    public UserDto deleteUser(@PathVariable String email) {
        User user = userService.validateAndGetUserByEmail(email);
        userService.deleteUser(user);
        return userMapper.toUserDto(user);
    }

    // Método para alterar o papel de um usuário.
    // A anotação @Operation do Swagger é usada para fornecer detalhes sobre esta operação na documentação da API.
    @Operation(security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)})
    @PutMapping("/change-role/{email}/{newRole}")
    public ResponseEntity<Void> changeUserRole(@PathVariable String email, @PathVariable String newRole) {
        try {
            User user = userService.validateAndGetUserByEmail(email);
            if ("Master".equals(user.getName())) {
                throw new IllegalArgumentException("Cannot change the role of Master users");
            }
            userService.changeUserRole(email, newRole);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}