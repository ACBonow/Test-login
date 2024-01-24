package com.arthurbonow.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Este arquivo define a classe BookApiApplication que é a classe principal da aplicação Spring Boot. Esta classe é responsável por iniciar a aplicação.

// A anotação @SpringBootApplication é uma anotação de conveniência que adiciona todas as seguintes anotações:
// @Configuration: Marca a classe como uma fonte de definições de beans para o contexto da aplicação.
// @EnableAutoConfiguration: Diz ao Spring Boot para começar a adicionar beans com base nas configurações de classpath, outras beans e várias configurações de propriedades.
// @ComponentScan: Diz ao Spring para procurar outras componentes, configurações e serviços no pacote com.arthurbonow.loginapi, permitindo que ele encontre os controladores.
@SpringBootApplication
public class BookApiApplication {

    // O método main é o ponto de entrada da aplicação.
    public static void main(String[] args) {
        // A classe SpringApplication é usada para iniciar uma aplicação Spring Boot a partir da linha de comando.
        // O método run inicia a execução da aplicação.
        SpringApplication.run(BookApiApplication.class, args);
    }
}