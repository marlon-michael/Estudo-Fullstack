package view.repository;

import java.util.List;
import model.entitiy.UserEntity;


// um reposotório tem a tarefa de modelas as seleções e buscas 
// que serão utilizadas no projeto

// modelagem de consultas da tabela User (id, username, password)
public interface UserRepository {
    List<UserEntity> findAll(); // busca tudo de User
    UserEntity findByUsername(String username); // busca User pelo "username"
    UserEntity save(UserEntity user);
}
