package com.entra21.springproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @PostMapping("/")
    public String greet(@RequestBody String user){
        return "Hello "+user;
    }
}
