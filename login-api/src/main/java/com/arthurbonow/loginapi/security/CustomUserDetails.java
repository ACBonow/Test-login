package com.arthurbonow.loginapi.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;
//Este arquivo define a classe CustomUserDetails que implementa a interface UserDetails do Spring Security. Esta
// classe é usada para representar um usuário autenticado na aplicação. Ela contém detalhes sobre o usuário, como
// email, senha, nome, CPF, papel e autoridades.

// A anotação @Data do Lombok gera automaticamente getters, setters, equals, hashCode e toString para os campos da classe.
@Data
// A classe implementa a interface UserDetails do Spring Security.
public class CustomUserDetails implements UserDetails {

    // Campos da classe que armazenam os detalhes do usuário.
    private UUID id;
    private String email;
    private String password;
    private String name;
    private String cpf;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    // Método para obter as autoridades do usuário. As autoridades são usadas para controle de acesso na aplicação.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Método para obter a senha do usuário.
    @Override
    public String getPassword() {
        return password;
    }

    // Método para obter o nome de usuário. Neste caso, o email é usado como nome de usuário.
    @Override
    public String getUsername() {
        return email;
    }

    // Método para verificar se a conta do usuário não expirou.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Método para verificar se a conta do usuário não está bloqueada.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Método para verificar se as credenciais do usuário não expiraram.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Método para verificar se o usuário está habilitado.
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Método para definir o nome de usuário. Neste caso, o email é usado como nome de usuário.
    public void setUsername(String email) {
        this.email = email;
    }
}