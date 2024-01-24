package com.arthurbonow.loginapi.mapper;

import com.arthurbonow.loginapi.model.User;
import com.arthurbonow.loginapi.rest.dto.UserDto;
import org.springframework.stereotype.Service;
//Este arquivo define uma classe UserMapperImpl que implementa a interface UserMapper. Esta classe é responsável por
// converter um objeto User em um objeto UserDto. Isso é útil quando queremos enviar dados do usuário para o cliente,
// mas não queremos expor todos os detalhes do objeto User.


// A anotação @Service indica que esta classe é um serviço do Spring.
// Isso significa que o Spring irá criar uma instância desta classe e a gerenciará como um bean.
@Service
public class UserMapperImpl implements UserMapper {

    // Este método é a implementação do método declarado na interface UserMapper.
    // Ele é usado para converter um objeto User em um objeto UserDto.
    @Override
    public UserDto toUserDto(User user) {
        // Se o objeto User for null, o método retorna null.
        if (user == null) {
            return null;
        }
        // Cria um novo objeto UserDto com os detalhes do objeto User e retorna.
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getCpf(), user.getRole());
    }
}