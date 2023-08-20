package com.restapi.controller;

import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public User findByName(@PathVariable Long id){
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        else return null;
    }

    @PostMapping("/post")
    public User saveUser(@RequestBody User newUser){
        try{
            newUser.setName(newUser.getName().replace("@", " "));
            userRepository.save(newUser);
            return newUser;
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            return null;
        }

    }

    @PutMapping("/patch/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        try{
            User user = new User();
            if (userRepository.findById(id).isPresent()) {
                user = userRepository.findById(id).get();
                user.setName(updatedUser.getName().replace("@", " "));
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

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id){
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
