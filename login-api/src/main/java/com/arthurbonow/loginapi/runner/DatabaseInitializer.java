package com.arthurbonow.loginapi.runner;

import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.security.WebSecurityConfig;
import com.arthurbonow.loginapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
// A anotação @Slf4j do Lombok adiciona um logger à classe.
// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @Component indica que esta classe é um componente do Spring e deve ser gerenciada pelo container do Spring.
@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    // Injeção de dependências.
    private final UserService userService;

    // O método run é um método de callback que é chamado quando a aplicação é iniciada.
    @Override
    public void run(String... args) {
        // Se já existem usuários no banco de dados, o método retorna e não faz nada.
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        // Para cada usuário na lista USERS, o método saveUser do userService é chamado para salvar o usuário no banco de dados.
        USERS.forEach(userService::saveUser);
        // Uma mensagem é registrada para indicar que o banco de dados foi inicializado.
        log.info("Database initialized");
    }

    // Esta é uma lista de usuários que serão adicionados ao banco de dados quando a aplicação for iniciada.
    private static final List<User> USERS = Arrays.asList(
            // Criação de novos objetos User.
            new User("master@mycompany.com", "master", "Master", "000.000.000-00", WebSecurityConfig.ADMIN),
            new User("user@mycompany.com", "user", "User", "123.456.789-02", WebSecurityConfig.USER),
            new User("bruno@mycompany.com", "user", "Bruno", "123.456.789-03", WebSecurityConfig.USER),
            new User("marcos@mycompany.com", "user", "Marcos", "123.456.789-04", WebSecurityConfig.USER),
            new User("leonardo@mycompany.com", "user", "Leonardo", "123.456.789-05", WebSecurityConfig.USER),
            new User("fabio@mycompany.com", "user", "Fabio", "123.456.789-06", WebSecurityConfig.USER),
            new User("maria@mycompany.com", "user", "Maria", "123.456.789-07", WebSecurityConfig.USER),
            new User("lulia@mycompany.com", "user", "Julia", "123.456.789-08", WebSecurityConfig.USER)
    );
}