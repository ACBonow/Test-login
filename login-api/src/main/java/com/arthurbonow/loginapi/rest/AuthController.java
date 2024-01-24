package com.arthurbonow.loginapi.rest;

import com.arthurbonow.loginapi.exception.DuplicatedUserInfoException;
import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.repository.UserRepository;
import com.arthurbonow.loginapi.rest.dto.AuthResponse;
import com.arthurbonow.loginapi.rest.dto.LoginRequest;
import com.arthurbonow.loginapi.util.CpfValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import com.arthurbonow.loginapi.rest.dto.SignUpRequest;
import com.arthurbonow.loginapi.security.WebSecurityConfig;
import com.arthurbonow.loginapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//Este arquivo define a classe AuthController que é um controlador REST no Spring Boot. Este controlador lida com as
// solicitações HTTP relacionadas à autenticação e ao cadastro de usuários.

// A anotação @CrossOrigin permite que recursos sejam acessados por domínios diferentes, neste caso, 'http://localhost:3000'.
// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @RestController indica que esta classe é um controlador REST.
// A anotação @RequestMapping define o caminho base para as rotas neste controlador.
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Injeção de dependências.
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para autenticar um usuário. Retorna um ResponseEntity com um objeto AuthResponse se a autenticação for bem-sucedida.
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.validEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Método para cadastrar um novo usuário. Retorna um objeto AuthResponse se o cadastro for bem-sucedido.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }
        if (!CpfValidator.cpfIsValid(signUpRequest.getCpf())) {
            throw new IllegalArgumentException("Invalid CPF, insert a valid CPF");
        }
        if ("Master".equals(signUpRequest.getName())) {
            throw new IllegalArgumentException("Cannot create a user with the name Master");
        }
        User user = userService.saveUser(createUser(signUpRequest));
        return new AuthResponse(user.getId(), user.getName(), user.getRole());
    }

    // Método para redefinir a senha de um usuário. Retorna um objeto AuthResponse se a redefinição de senha for bem-sucedida.
    @PutMapping ("/recovery-password")
    public AuthResponse recoveryPassword (@Valid @RequestBody SignUpRequest signUpRequest) {
        if (!userService.existsByEmail(signUpRequest.getEmail())) {
            throw new ResourceNotFoundException("User not found with provided EMAIL");
        } else if (!userService.existsByName(signUpRequest.getName())) {
            throw new ResourceNotFoundException("User not found with provided NAME");
        } else if (!userService.existsByCpf(signUpRequest.getCpf())) {
            throw new ResourceNotFoundException("User not found with provided CPF");
        } else {
            Optional<User> userOptional = userService.findByEmail(signUpRequest.getEmail());
            User user = userOptional.get();
            if ("Master".equals(user.getName())) {
                throw new IllegalArgumentException("We cannot change the password for Master users, please contact the administrator");
            }
            userService.deleteUser(user);
            user = userService.saveUser(createUser(signUpRequest));
            return new AuthResponse(user.getId(), user.getName(), user.getRole());
        }
    }

    // Método auxiliar para criar um objeto User a partir de um objeto SignUpRequest.
    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setCpf(signUpRequest.getCpf());
        user.setRole(WebSecurityConfig.USER);
        return user;
    }

}