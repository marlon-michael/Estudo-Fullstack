package controller;

import java.util.List;

import model.DTO.UserDTO;
import view.service.UserService;

// um controller faz o mapeamento e controle das rotas
// entrada e saída de dados por meio de argumentos e do corpo da requisição

// rota principal
//@RequestMapping("/user")
public class UserController{
    // instancia de service
    private UserService userService;

    //@GetMapping("/all")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    //@GetMapping("/find/{username}") // path variable: String username
    public UserDTO findByUsername(String username){
        // requisita o argumento "username" e mapeia para a variavel "username"
        return userService.findByUsername(username);
    }

    //@PostMapping("/add")
    // request body: UserDTO newUser
    public UserDTO saveUser(UserDTO newUser){
        // requisita o corpo de requisição e mapeia para "newUser"
        return userService.saveUser(newUser, null);
    }
}