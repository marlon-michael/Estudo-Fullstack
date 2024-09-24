package com.spring.security.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desabilita proteção CSRF
            .httpBasic(withDefaults())
            .authorizeHttpRequests(req -> req
                    // .antMatchers("/usuario/all").hasRole("ADMIN")
                    .anyRequest().permitAll() // solicita autenticação para qualquer rota
            );
        return http.build();
    }

    // Autenticação em memória
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN") // nível de acesso
            .build());
        return userDetailsManager;
    }

    // Encriptador de senha
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

// @Configuration
// public class WebSecurityConfig {

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .csrf().disable() // desabilita proteção CSRF
// .httpBasic()
// .and().authorizeHttpRequests(req -> req
// // .antMatchers("/usuario/all").hasRole("ADMIN")
// .anyRequest().permitAll() // solicita autenticação para qualquer rota
// );
// }

// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception{
// auth
// .inMemoryAuthentication()
// .withUser("admin")
// .password("admin")
// .roles("ADMIN");
// }

// }
