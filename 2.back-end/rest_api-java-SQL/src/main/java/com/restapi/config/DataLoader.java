package com.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;

@Configuration
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Override
    public void run (String... args){
        User user = new User();
        user.setName("Marlon");
        userRepository.save(user);
    }
}
