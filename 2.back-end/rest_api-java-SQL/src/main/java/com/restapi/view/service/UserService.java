package com.restapi.view.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public void save(User newUser) throws Exception {
        if (newUser == null) throw new Exception("Novo usuário deve ser enviado no corpo da requsição");
        if (newUser.getName() == null || newUser.getName() == "") throw new Exception("Nome do usuário não pode ser vazio");
        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            throw new Exception("Erro ao salvar usuario");
        }
        
    }

    public User updatedUser(Long id, User newUser){
        try{
            User user = new User();
            if (userRepository.findById(id).isPresent()) {
                user = userRepository.findById(id).get();
                user.setName(user.getName().replace("@", " "));
                userRepository.save(user);
                return user;
            }
            else return null;
        }
        catch (Exception error){
            System.out.println(error.getMessage());
            return null;
        }
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User deleteUser(Long id) {
        try{
            User user = new User();
            if (userRepository.findById(id).isPresent()){
                user = userRepository.findById(id).get();
                userRepository.deleteById(id);
                return user;
            }
            else return null;
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            return null;
        }
    }
}
