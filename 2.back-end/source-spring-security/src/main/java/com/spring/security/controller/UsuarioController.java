package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.configuration.TokenService;
import com.spring.security.model.RoleEntity;
import com.spring.security.model.RoleNameEnum;
import com.spring.security.model.UserEntity;
import com.spring.security.view.RoleRepository;
import com.spring.security.view.UserRepository;
import com.spring.security.view.UserService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UserService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        UserEntity user = usuarioService.getUserByUsername(username);
        if (user == null) return null;
        if (passwordEncoder.matches(password, user.getPassword())){
            return tokenService.generateToken(user);
        }
        return null;
    }

    @GetMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        UserEntity user = usuarioService.getUserByUsername(username);
        if (user == null) {
            user = new UserEntity();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(RoleNameEnum.USER));
            user.setRoles(roles);
            usuarioService.addUser(user);
            return tokenService.generateToken(user);
        }
        return null;
    }
    
    @GetMapping("/all")
    public List<UserEntity> getAllUsuarios() {
        return usuarioService.getAllUsers();
    }

    @GetMapping
    public UserEntity getUsuarioByUsername(@RequestParam String username) {
        return usuarioService.getUserByUsername(username);
    }

    @PostMapping
    public UserEntity addUsuario(@RequestParam String login, @RequestParam String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return usuarioService.addUser(new UserEntity(login, encoder.encode(pass)));
    }

    @PreAuthorize("hasAuthority('ADMIN')") // autorização a nível de metodo
    @DeleteMapping("/delete")
    public void deleteUsers(){
        usuarioService.removeUsers();
    }

    @Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

    @GetMapping("/init")
    public void initalize() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        roleRepository.save(new RoleEntity(RoleNameEnum.ADMIN));
        roleRepository.save(new RoleEntity(RoleNameEnum.USER));
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleNameEnum.ADMIN));
        userRepository.save(new UserEntity("admin", new BCryptPasswordEncoder().encode("admin"), roles));
        roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleNameEnum.USER));
        userRepository.save(new UserEntity("user", new BCryptPasswordEncoder().encode("user"), roles));
        roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleNameEnum.USER));
        userRepository.save(new UserEntity("user2", new BCryptPasswordEncoder().encode("user"), roles));
    }
    
    
}
