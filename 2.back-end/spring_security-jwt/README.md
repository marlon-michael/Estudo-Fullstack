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
- [Autenticação com JWT Token](#autenticação-jwt---tokens)
    - [Dependencias JWT](#dependencias-jwt)
    - [Configuração da chave privada](#configuração-da-chave-do-token-jwt)
    - [Configuração do Token](#configuração-da-autenticação-jwt)
    - [Filtro de autenticação JWT](#filtro-de-autenticação-jwt)
    - [Configuração da autenticação](#configuração-da-autenticação-jwt)
    - [Registro e login com token](#registro-e-login-jwt)

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
    - .../configuration/SecurityConfig.java
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
    - .../configuration/SecurityConfig.java
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
    - .../model/UserEntity.java
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
    - .../view/MyUserDetailService.java
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

### Autenticação JWT - Tokens
- #### Dependencias JWT
    - pom.xml
    ```xml
    <dependency> <!-- Codificação e decodificação com JWT -->
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>4.4.0</version>
    </dependency>
    ```

- #### Configuração da chave do token JWT
    - .../resources/application.properties
    ```xml
    jwt.token.secret=meu-token-secreto <!-- chave privada para autenticação e verificação dos tokens -->
    ```

- #### Configuração JWT
    - .../configuration/TokenService.java
    ```java
    import java.time.Instant;
    import java.time.LocalDateTime;
    import java.time.ZoneOffset;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import com.auth0.jwt.JWT;
    import com.auth0.jwt.algorithms.Algorithm;
    import com.spring.security.model.UserEntity;

    @Service
    public class TokenService{
        // Busca chave secreta de application.properties
        @Value("${jwt.token.secret}")
        private String secret;

        public String generateToken(UserEntity user){
            try{
                // cria algoritmo de codificação usando chave secreta
                Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.create()
                    .withIssuer("jwt-auth") // defini titulo do token
                    .withSubject(user.getUsername()) // define corpo
                    .withExpiresAt(generateExpirationDate()) // define data de expiração (2h)
                    .sign(algorithm); // assina token usando algoritimo com chave secreta
            }catch(Exception error){
                return null;
            }
        }

        public String validateToken(String token){
            try{
                Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.require()
                    .withIssuer("jwt-auth") // verifica titulo do token
                    .build() // decodifica token
                    .verify(token) // verifica autenticidade to token
                    .getSubject() // busca o corpo do token (username)
            }catch(Exception error){
                return null;
            }
        }

        // Retorna data e hora atual acrescido de duas horas
        public Instant generateExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        }
    }
    ```

- #### Filtro de autenticação JWT
    - .../configuration/SecurityFilter.java
    ```java
    import java.io.IOException;
    import java.util.Collection;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;
    import com.spring.security.model.UserEntity;
    import com.spring.security.view.UserRepository;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    @Component
    // entendendo componente executado uma vez por requisição
    public SecurityFilter extends OncePerRequestFilter{
        @Autowired
        TokenService tokenService;
        @Autowired
        UserRepository userRepository;

        @Override
        // resolvendo requisição
        public void doFilterInternal(HttpServerletRequest request, HttpServerletReponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = recoverToken(request); // recupera token do header
            String username = tokenService.validateToken(token); // valida token
            if (username != null){
                UserEntity user = userRepository.findUserByUsername(username);
                if (user == null) throw UserNotFoundException("Usuario não encontrado");
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities(); // define as autoritedes do usuario
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities); // // autentica novo usuario
                SecurityContextHolder.getContext.setAuthentication(auth); // aduciona usuario ao contexto da aplicação
            }
            filterChain.doFilter(response, request); // segue com a requisição
        }

        private recoverToken(HttpServerletRequest request){
            String token = request.getHeader("Authorization"); // recupera token d e autorização do header
            if (token == null) return null;
            return token.replace("Bearer ", ""); // retorna apenas o token jwt
        }
    }
    ```

- #### Configuração da autenticação JWT
    - .../configuration/SecurityConfig.java
    ```java
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig
    @Autowired
    SecurityFilter filter;

    [...]
    
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
            // Desabilita gerenciamento de sessão no servidor
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Adiciona filtro a requisição
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(req -> req
                .requestMatchers(HttpMethod.GET, "/usuario/register").permitAll() // permite usuarios deslogados se registrarem
                .requestMatchers(HttpMethod.GET, "/usuario/login").permitAll() // permite usuarios deslogados fazerem login
                .anyRequest().authenticated() // solicita autenticação para qualquer rota
            );
        return http.build();
    }
    ```

- #### Registro e Login JWT
    - .../controller/UsuarioController.java
    ```java
    [...]
    @PostMapping("/login")
    public String registerUser(@RequestBody UserEntity newUser){
        UserEntity user = userService.findByUsername(newUser.getUsername());
        if (user != null) return null;
        userService.save(newUser);
        return tokenService.generateToken(newUser); // retorna token do usuario
    }

    @PostMapping("/register")
    public String loginUser(@RequestBody UserEntity loggingUser){
        UserEntity user = userService.findByUsername(loggingUser.getUsername());
        if (user == null) return null;
        return tokenService.generateToken(loggingUser); // retorna token do usuario
    }
    [...]
    ```

### Cargos e Autoridade

- #### Enum de cargo
    - .../model/RoleNameEnum.java
    ```java
    public enum RoleNameEnum{
        ADMIN,
        COMMOM_USER
    }
    ```

- #### Entidade de Cargo
    - .../model/RoleEntity.java
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
    - .../model/UserEntity.java
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
    - .../controller/UserController.java
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