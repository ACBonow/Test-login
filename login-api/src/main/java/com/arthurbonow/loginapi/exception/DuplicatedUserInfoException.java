package com.arthurbonow.loginapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//Este arquivo define uma classe de exceção personalizada chamada DuplicatedUserInfoException que é lançada quando
// ocorre uma tentativa de criar um usuário com informações duplicadas, como email ou CPF, que já existem no sistema.

// A anotação @ResponseStatus é usada para definir o código de status HTTP que será retornado quando essa exceção for lançada.
// Neste caso, o código de status é CONFLICT, que indica que a solicitação não pôde ser concluída devido a um conflito com o estado atual do recurso.
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedUserInfoException extends RuntimeException {

    // Este é o construtor da classe de exceção. Ele aceita uma mensagem de erro como parâmetro, que será a mensagem associada à exceção.
    public DuplicatedUserInfoException(String message) {
        super(message);
    }
}