package com.arthurbonow.loginapi.repository;

import com.arthurbonow.loginapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Este arquivo define a interface UserRepository que estende JpaRepository. Esta interface é usada para realizar
// operações de banco de dados na tabela users. O Spring Data JPA fornece métodos como save(), delete(), findAll(),
// etc., e permite a criação de novos métodos de consulta apenas definindo a assinatura do método.

// A anotação @Repository é usada para indicar que a classe é um repositório Spring Data.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Este método é usado para encontrar um usuário pelo email. Retorna um Optional que pode conter o User se encontrado.
    Optional<User> findByEmail(String email);

    // Este método é usado para verificar se um usuário com o email fornecido já existe no banco de dados.
    boolean existsByEmail(String email);

    // Este método é usado para verificar se um usuário com o CPF fornecido já existe no banco de dados.
    boolean existsByCpf(String cpf);

    // Este método é usado para verificar se um usuário com o nome fornecido já existe no banco de dados.
    boolean existsByName(String name);

    // Este método é usado para encontrar um usuário pelo email, nome e CPF. Retorna um Optional que pode conter o User se encontrado.
    Optional<User> findByEmailAndNameAndCpf(String email, String name, String cpf);
}