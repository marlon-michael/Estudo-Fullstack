package com.spring.security.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.model.MyUserPrincipal;
import com.spring.security.model.UserEntity;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByLogin(username);
        if (user == null) {
            System.out.println("não encontrado");
            throw new UsernameNotFoundException(username);
        }
        System.out.println("encontrado");
        return new MyUserPrincipal(user);
    }
}
