# Autenticação com Spring Security - Java 22

- [Primeiros passos](#primeiros-passos)
    - [configuração](#configuração-inicial)
    ---
- [Autenticação em memoria](#autenticação-em-memória)
    - [configuração](#configuração-em-memoria)
    ---
- [Autenticação por entidade](#autenticação-por-entidade)
    - [configuração](#entidade-user)
    - [user detail sarvice](#implementação-de-userdetailservice)
    ---
- [Cargos e autoridades](#cargos-e-autoridade)
    - [enum de cargos](#enum-de-cargo)
    - [entidade de cargo](#entidade-de-cargo)
    - [entidade de usuario com cargo](#entidade-de-usuario-com-cargo)
    ---
- [Rotas](#rotas-e-autenticação)
    - [autenticação a nivel de metodo](#controllers)
    ---

### Dependencias
- #### Maven
    - pom.xml
    ```xml
    [...]
    <dependency> <!-- Spring Boot Web -->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency> <!-- Spring Security -->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <dependency> <!-- Spring Boot JPA (manipulação de banco de dados)-->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency> <!-- Conexão com banco de dados -->
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    [...]
    ```

### Primeiros passos

- #### Configuração inicial
    - com/spring/security/configuration/SecurityConfig.java
    ```java
    import static org.springframework.security.config.Customizer.withDefaults;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableMethodSecurity // permite a configuração de autenticação em cada metodo utilizando @PreAuthorize("hasAuthority('USER')")
    public class SecurityConfig{
        // encriptador de senhas
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        // definição de autenticação das rotas
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http
                .csrf(csrf -> csrf.disable()) // desabilita proteção CSRF
                .httpBasic(withDefaults())
                .authorizeHttpRequests(req -> req
                    .requestMatchers(HttpMethod.POST, "/usuario").permitAll() // permite usuarios deslogados acessarem POST de /usuario
                    .anyRequest().authenticated() // solicita autenticação para qualquer rota
                );
            return http.build();
        }
    }
    ```

### Autenticação em memória

- #### Configuração em memoria
    - com/spring/security/configuration/SecurityConfig.java
    ```java
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    [...]
    public class SecurityConfig{
        [...]
        // Autenticação em memória
        // Não pode haver nenhum elemento de implementação de autenticação por entidade
        @Bean
        public UserDetailService userDetailService(){
            InMemoryUserDetailsManager userDetail = new InMemoryUserDetailsManager();
            userDetail.createuser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .authorities(new SimpleGrantedAuthority("ADMIN"))
                .build()
            );
            return userDetail;
        }
        [...]
    }
    ```

### Autenticação por Entidade

- #### Entidade User
    - com/spring/security/model/UserEntity.java
    ```java
    import java.util.Collection;
    import java.util.List;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Data // getters & setters por lombok
    @Table("users")
    @Entity
    public class UserEntity implements UserDetails{
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @Column(name="username", unique=true, nullable=true)
        private String username;

        @Column(name="username", nullable=true)
        private String password;

        @Column(name="role");
        private String role;

        // retorna a autoridade do usuario
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities(){
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (this.role != null) authorities.add(new SimpleGrantedAuthority(this.role));
            else authorities.add(new SimpleGrantedAuthority("COMMON_USER"));
            return authorities;
        }
        // metodos de verificação da ativação da conta
        @Override
        public boolean isAccountNonExpired() {return true;}
        @Override
        public boolean isAccountNonLocked() {return true;}
        @Override
        public boolean isCredentialsNonExpired() {return true;}
        @Override
        public boolean isEnabled() {return true;}
    }
    ```

- #### implementação de UserDetailService
    - com/spring/security/view/MyUserDetailService.java
    ```java
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import com.spring.security.model.UserEntity;

    @Service
    public class MyUserDetailService implements UserDetailService{
        @Autowired
        UserRepository userRepository;
        // metodo para busca e autenticação do usuario logando
        @Override
        public UserDetails loadUserByUsername(String username){
            UserEntity user = userRepository.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException(username);
            return new User(user.getUsername(), user.getPassword(), user.getAuthorities())
        }
    }
    ```

### Cargos e Autoridade

- #### Enum de cargo
    - com/spring/security/model/RoleNameEnum.java
    ```java
    public enum RoleNameEnum{
        ADMIN,
        COMMOM_USER
    }
    ```

- #### Entidade de Cargo
    - com/spring/security/model/RoleEntity.java
    ```java
    import org.springframework.security.core.GrantedAuthority;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Data
    @Table("roles")
    public class RoleEntity implements GrantedAuthority{
        @Id
        @GeneratedValue(strategy=GanerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @EnumaratedValue(strategy=EnumType.STRING)
        @Column(name="name",unique=true,nullable=false)
        private RoleNameEnum name;

        public String getAuthority(){
            return name.toString();
        }
    }
    ```

- #### Entidade de Usuario com cargo
    - com/spring/security/model/UserEntity.java
    ```java
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.JoinTable;
    import jakarta.persistence.ManyToMany;

    [...]
    public class UserEntity implements UserDetails {
        [...]
        // mapeamento da tabela que armazena quais usuarios possuem quais cargos
        @ManyToMany
        @JoinTable(name="users_roles"
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id")
        )
        private List<RoleEntity> roles;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.roles;
        }
        [...]
    }
    ```

### Rotas e autenticação

- #### Controllers
    - com/spring/security/controller/UserController.java
    ```java
    @RestController
    @RequestMapping("/user")
    public class UserController{
        [...]
        @PreAuthorize("hasAuthority('ADMIN')") // configuração de autenticação a nível de metodo
        @GetMapping
        public List<UserEntity> getUsers(){ return userRepository.findAll(); }

        @PostMaping 
        public void saveUser(UserEntity user){ userRepository.save(user); }
    }
    ```