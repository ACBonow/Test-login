package com.arthurbonow.loginapi.mapper;

import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.rest.dto.UserDto;
//Este arquivo define uma interface chamada UserMapper que é usada para mapear entre a entidade User e o DTO UserDto.
// DTOs (Data Transfer Objects) são objetos simples que são usados para transferir dados entre subsistemas de uma
// aplicação, neste caso, entre a camada de persistência e a camada de apresentação.

// Esta é a declaração da interface UserMapper.
public interface UserMapper {

    // Este método é usado para converter um objeto User em um objeto UserDto.
    UserDto toUserDto(User user);
}