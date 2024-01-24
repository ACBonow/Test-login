package com.arthurbonow.loginapi.service;

import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Este arquivo define a classe UserServiceImpl que é um serviço no Spring Boot. Esta classe é uma implementação da
// classe abstrata UserService e é usada para realizar operações relacionadas ao usuário, como obter usuários,
// validar email e senha, salvar usuário, verificar a existência de um usuário, deletar usuário, entre outros.
// Essas operações são realizadas principalmente através do UserRepository.

// A anotação @Service indica que esta classe é um serviço do Spring.
@Service
public class UserServiceImpl extends UserService {

    // Injeção de dependências.
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // Construtor da classe que recebe as dependências necessárias.
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository, passwordEncoder);
    }

    // Método para obter todos os usuários.
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Método para obter um usuário pelo email.
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Método para validar e obter um usuário pelo email.
    @Override
    public User validateAndGetUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Email %s not found", email)));
    }

    // Método para verificar se existe um usuário com o email fornecido.
    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Método para validar o email e a senha de um usuário.
    @Override
    public Optional<User> validEmailAndPassword(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    // Método para salvar um usuário.
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Método para deletar um usuário.
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}