package com.spring.security.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.model.UserEntity;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public UserEntity addUser(UserEntity user){
        userRepository.save(user);
        return user;
    }

    public void removeUsers(){
        userRepository.deleteAll();
    }
}
