package view.service;

import java.util.List;
import java.util.stream.Collectors;

import model.DTO.UserDTO;
import model.entitiy.UserEntity;
import view.repository.UserRepository;

// faz tratamento de erros e aplica regras de negócio

public class UserService {
    // instancia do repositório de User
    private UserRepository userRepository;

    public List<UserDTO> findAll(){
        // itera sobre os valores reebidos do repositório
        // mapeia as entidades de User para UserDTO
        // retorna UserDTO
        return userRepository.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            return userDTO;
        }).collect(Collectors.toList());
    }

    public UserDTO findByUsername(String username){
        // mapeia as entidades de User para UserDTO
        // retorna UserDTO
        UserEntity user = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public UserDTO saveUser(UserDTO newUser, String creditCard){
        // mapeia userDTO para uma entidade e salva no banco de dados
        // faz verificação das regras de negócios
        if (newUser.getPassword().length() < 8) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUsername(null);
        user.setPassword(null);
        user.setCreditCard(creditCard);
        userRepository.save(user);
        return newUser;
    }

}
