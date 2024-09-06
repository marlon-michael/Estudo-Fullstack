package com.restapi.controller;

import com.restapi.model.entity.User;
import com.restapi.view.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/get/{name}")
    public User findByName(@PathVariable String name){
        return userService.findByName(name).get(0);
    }

    @PostMapping("/post")
    public User saveUser(@RequestBody User newUser){
        try{
            newUser.setName(newUser.getName().replace("@", " "));
            userService.save(newUser);
            return newUser;
        }
        catch(Exception error){
            System.out.println(error.getMessage());
            return null;
        }

    }

    @PutMapping("/patch/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return userService.updatedUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
