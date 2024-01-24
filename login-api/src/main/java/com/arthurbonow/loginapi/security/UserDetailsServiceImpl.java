package com.arthurbonow.loginapi.security;

import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
//Este arquivo define a classe UserDetailsServiceImpl que implementa a interface UserDetailsService do Spring Security.
//Esta classe é usada para carregar os detalhes do usuário durante a autenticação.

// A anotação @RequiredArgsConstructor do Lombok gera um construtor com parâmetros obrigatórios (final ou @NonNull).
// A anotação @Service indica que esta classe é um serviço do Spring.
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Injeção de dependências.
    private final UserService userService;

    // Este método é chamado durante a autenticação para carregar os detalhes do usuário pelo nome de usuário (neste caso, o email).
    @Override
    public UserDetails loadUserByUsername(String email) {
        // Busca o usuário pelo email. Se o usuário não for encontrado, lança uma exceção.
        User user = userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", email)));
        // Cria uma lista de autoridades a partir do papel do usuário.
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        // Mapeia o usuário para um objeto CustomUserDetails e retorna.
        return mapUserToCustomUserDetails(user, authorities);
    }

    // Este método auxiliar é usado para mapear um objeto User para um objeto CustomUserDetails.
    private CustomUserDetails mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getEmail());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setName(user.getName());
        customUserDetails.setEmail(user.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}