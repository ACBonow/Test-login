package com.arthurbonow.loginapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
//Este arquivo define a entidade User que representa a tabela users no banco de dados. Esta entidade é usada pelo
// Hibernate, um framework de persistência de dados, para mapear os registros da tabela users para objetos User em Java.

// A anotação @Data do Lombok gera automaticamente getters, setters, equals, hashCode e toString para os campos da classe.
// A anotação @NoArgsConstructor do Lombok gera automaticamente um construtor sem argumentos.
@Data
@NoArgsConstructor
// A anotação @Entity indica que esta classe é uma entidade JPA.
@Entity
// A anotação @Table é usada para fornecer detalhes sobre a tabela que será usada para persistir a entidade no banco de dados.
@Table(name = "users", uniqueConstraints = {
        // As restrições de unicidade garantem que não haverá dois usuários com o mesmo email ou cpf.
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "cpf")
})
public class User {

    // A anotação @Id indica que o campo abaixo é a chave primária da entidade.
    @Id
    // As anotações @GenericGenerator e @GeneratedValue são usadas para indicar que o valor deste campo será gerado automaticamente.
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    // Estes são os campos da entidade User. Cada campo é mapeado para uma coluna na tabela do banco de dados.
    private String email;
    private String password;
    private String name;
    private String cpf;
    private String role;

    // Este é um construtor que permite criar uma nova instância de User com todos os campos definidos.
    public User(String email, String password, String name, String cpf, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.role = role;
    }
}