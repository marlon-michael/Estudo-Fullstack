package com.spring.security.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.model.UserEntity;

@Service
public class UsuarioService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsuarios(){
        return userRepository.findAll();
    }

    public UserEntity getUsuarioByUsername(String username){
        return userRepository.findByLogin(username);
    }
    
    public UserEntity addUsuario(UserEntity user){
        userRepository.save(user);
        return user;
    }
}
