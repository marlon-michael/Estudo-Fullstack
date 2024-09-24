package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.UserEntity;
import com.spring.security.view.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/all")
    public List<UserEntity> getAllUsuarios() {
        System.out.println("all");
        return usuarioService.getAllUsuarios();
    }

    @GetMapping
    public UserEntity getUsuarioByUsername(@RequestParam String username) {
        System.out.println("by username");
        return usuarioService.getUsuarioByUsername(username);
    }

    @PostMapping
    public UserEntity addUsuario(@RequestParam String login, @RequestParam String pass) {
        System.out.println("save");
        return usuarioService.addUsuario(new UserEntity(login, pass));
    }
    
}
