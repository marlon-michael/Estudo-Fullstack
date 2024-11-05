package com.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // permite a configuração de autenticação em cada metodo utilizando @PreAuthorize("hasAuthority('USER')")
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    // Encriptador de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desabilita proteção CSRF
            // .httpBasic(withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Desabilita o gerenciamento de seção no lado do servidor (Token JWT)
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // aplica filtro de autenticação de token
            .authorizeHttpRequests(req -> req
                .requestMatchers(HttpMethod.GET, "/usuario/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario").permitAll() // permite usuarios deslogados acessarem POST de /usuario
                .requestMatchers(HttpMethod.GET, "/usuario/init").permitAll() // permite usuarios deslogados acessarem POST de /usuario
                .requestMatchers(HttpMethod.GET, "/usuario/all").hasAuthority("ADMIN") // permite que usuarios com cargo de ADMIN acessem
                .anyRequest().authenticated() // solicita autenticação para qualquer rota
            );
        return http.build();
    }

    // Autenticação em memória
    // Não pode haver nenhum elemento de implementação de autenticação em banco de dados para funcionar
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    //     userDetailsManager.createUser(User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         .roles("ADMIN") // nível de acesso / cargo
    //         .authorities(new SimpleGrantedAuthority("ADMIN"))
    //         .build());
    //     return userDetailsManager;
    // }

}
