package com.arthurbonow.loginapi.service;

import com.arthurbonow.loginapi.repository.UserRepository;
import com.arthurbonow.loginapi.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Este arquivo define a classe UserService que é um serviço no Spring Boot. Esta classe é usada para realizar operações
// relacionadas ao usuário, como obter usuários, validar email e senha, salvar usuário, verificar a existência de um
// usuário, deletar usuário, alterar o papel do usuário, entre outros. Essas operações são realizadas principalmente
// através do UserRepository.

// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @Service indica que esta classe é um serviço do Spring.
@RequiredArgsConstructor
@Service
public class UserService {

    // Injeção de dependências.
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Método para obter todos os usuários.
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Método para obter um usuário pelo email.
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Método para validar o email e a senha de um usuário.
    public Optional<User> validEmailAndPassword(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    // Método para validar e obter um usuário pelo email.
    public User validateAndGetUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Email %s not found", email)));
    }

    // Método para verificar se existe um usuário com o email fornecido.
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Método para salvar um usuário.
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Métodos para verificar se existe um usuário com o email, nome ou CPF fornecidos.
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }
    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    // Método para verificar se existe um usuário com o email, nome e CPF fornecidos.
    public boolean userExists(String email, String name, String cpf) {
        Optional<User> userOptional = userRepository.findByEmailAndNameAndCpf(email, name, cpf);
        return userOptional.isPresent();
    }

    // Método para deletar um usuário.
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Método para alterar o papel de um usuário.
    public void changeUserRole(String email, String newRole) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Email %s not found", email)));
        user.setRole(newRole);
        userRepository.save(user);
    }

    // Método para encontrar um usuário pelo email.
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}