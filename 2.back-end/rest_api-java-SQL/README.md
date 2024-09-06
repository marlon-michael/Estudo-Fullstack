# Spring boot API | Java + MySQL | MVC

- [Requerimentos e Dependencias](#requerimentos)
- [Configuração](#configuração-e-criação)
- [Estrutura de arquivos](#model-view-e-controller)
- [Rotas](#expondo-rotas)
- [Rodando o projeto](#hello-spring---rodando-o-projeto)
- [Teste manual](#requisição-http-postman-ui-curl-cli)
- [Testes automatizados](#testes-automatizados-nativo-springboot)


### Requerimentos
(verificar compatibiidade entre versões)
- IntelliJ IDEA Community (IDE)
- MySql
- Java (14.0.2)
    - Spring boot (2.7.3)
    - Maven: gerenciador de dependencias
        #### dependencias
        - Spring Web: requisiçoes web
        - Spring Data JPA: mapeamento de entidades
        - MySQL Driver: controlador MySql

### Configuração e criação
- mysql
    - instalação e definição das váriaveis de ambiente
    - rodando mysql
        ```console
        mysqld --console
        ```
    - configuração de usuario se senha
    - criação do banco de dados
        ```console
        create database [database-name];
        ```
- java - spring
    - maven com proxy (<diretório maven>/settings.xml)
        ```html
        ...
        <proxies>
            <proxy>
                <id>nome-do-proxy</id>
                <active>true</active>
                <protocol>https</protocol>
                <host>proxy.xx.com</host>
                <port>3128</port>
                <username>000000</username>
                <password>xxxxx</password>
                <nonProxyHosts>localhost</nonProxyHosts>
            </proxy>
        </proxies>
        ...
        ```
    - definições do projeto utilizando o spring initalizr
        - gerenciador de dependencias: Maven
        - linguagem: Java
        - spring boot version: 2.7.3 (JAVA 8 - 17)
        - java version: 11
        - dependencias: Spring Web, Spring Data JPA, MySQL Driver
    - configuração do spring boot: (src.main.resources.application.properties)
        ```json
        spring.datasource.url=jdbc:mysql://localhost:3306/[database-name] # string de conexão SQL
        spring.datasource.username=[database-username]
        spring.datasource.password=[database-pass]
        spring.datasource.driven-class-name=com.mysql.cj.jdbc.Driver
        #// manipulação do banco de dados durante execução do API
        #// - update: atualiza as tabelas conforme uso
        #// - create-drop: cria as tabelas e colunas e deleta ao fim da execução
        #// - create: apenas cria tabelas e colunas
        spring.jpa.hibernate.ddl-auto=none
        spring.jpa.show-sql=true
        ```
    - arquivo pom.xml
        ```html
        <!-- REINICIE O PROJETO PARA AS ALTERAÇÕES TEREM EFEITO -->
        [...]
        <artifactId>rabbitmq</artifactId>
        <!-- PODE CAUSAR ERROR DE ACORDO COM O SISTEMA OPERACIONAL
        - pom 
        - jar (para utilizar como um jar executável) -->
        <packaging>pom</packaging>
        <version>0.0.1-SNAPSHOT</version>
        [...]
        ```
        - dependencias
            - spring-boot-starter-amqp (interface de menssageria)
            - spring-boot-starter-web (bibliotecas web)
            - lombok (utilidades para desenvolvimento) 
        - plugins
            - spring-boot-maven-plugin

- IntelliJ
    - instalação das dependencias maven
    ```console
    mvn clean install
    ```

### Model, view e controller
- model
    - entity
    - DTO (data transfer object): camada de segurança evitando de expor dados da entity ao usuário
- view
    - repository
    - service
- controller
    - CRUD completo

### Expondo rotas
- crie uma novo pacote controller: src/main/java/com/[project]/controller/
  - crie um arquivo HelloController.java: HelloController.java
      ```java
      import org.springframework.web.bind.annotation.GetMapping;
      import org.springframework.web.bind.annotation.RestController;

      @RestController // anotação para definição do controller
      public class HelloController{
        @GetMapping("/hello") // anotação para exposição da rota com requisição GET
        public String hello(){
            return "Hello Spring";
        }

        // permite chamadas por aplicações front-end
        @Bean // configuração para chamadas CORS)
        public FilterRegistrationBean corsFilter(){
            final UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
            final CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // endereço da aplicação front-end
            config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept")); // configurações HEADER aceitas
            config.setAllowedMethods(Arrays.asList("GET", "POST", "UPDATE", "DELETE", "OPTIONS", "PATCH")); // metodos permitidos
            src.registerCorsConfiguration("/**", config); // rotas que sesão permitidas
            FilterRegistrationBean registration = new FilterRegistrationBean(new CorsFilter(src));
            return registration;
        }
      }
      ```

### Hello Spring - rodando o projeto
- IntelliJ
    - inicie o projeto ( src/main/java/com/[project-name]/[project-name]Application.java )
- terminal
    - rodando 
        ```console
        ./mvnw spring-boot:run
        ```
    - fazendo build para arquivo jar
        ```console
        ./mvnw clean package
        java -jar target/[generated-build].jar
        ```

### Requisição http: Postman (UI), curl (CLI)
- fazendo requisição GET na URL localhost:8080/hello
    ```html
    Hello Spring
    ```

### Testes automatizados nativo Springboot

#### Testes utilizando MySQL

- Os arquivos de teste devem ser criados seguindo a estrutura principal do projeto.
    - como exemplo de uma estrutura testada <b>src/main/com/exemplo/service/Service.java</b> os testes devem ser criados como <b>src/tests/exemplo/service/ServiceTest.java</b>


- requisitos (pom.xml)
    ```html
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```
- propriedades
    ```
    # CRIA E DELETA BASE DE TESTES DURANTE EXECUÇÃO
    spring.jpa.hibernate.ddl-auto=create-drop
    # BASE DE DADOS SEPARADA
    spring.datasource.url=jdbc:mysql://localhost:3306/rest_api_test
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
    ```
#### Testes unitários utilizando banco de dados
```java
package com.restapi.view.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;

// Anotações necessárias para testes com MySQL
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser(){
        // SALVANDO USUARIO
        User savingUser = new User();
        String username = "Marlon Michael";
        savingUser.setName(username);
        userRepository.save(savingUser);
        // BUSCANDO USUARIO
        User user = userRepository.findByName(username);
        // VERIFICANDO SE VALORES SÃO OS ESPERADOS
        assertNotNull(user);
        assertEquals(username, user.getName());
        assertEquals(1, user.size());
    }
}
```

#### Testes unitários com Mockito
- Mocks são dados padronizados de retorno. Deixando o foco dos testes para um unico objeto uma vez que retornam exatamente os dados necessários para o funcionamento da classe sem necessidade nem nenhum processamento.

```java
import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest(){
    @InjectMocks // objeto que será testado
    private ServiceClass serviceClass;

    @Mock // classe que irá retornar valores padronizados
    private RepositoryClass repositoryClass;

    User user;
	@BeforeEach
	public void setUp(){
		user = new User("Nome", "Sobrenome");
	}

    @Test
    public shouldFindUserByName(){
        when(repositoryClass.findByName("Nome")).thenReturn(user); // definição do retorno padrão
        User actualUser = repositoryClass.findByName("Nome");

        assertNotNull(actualUser);
        assertEquals(user.getName(), actualUser.getName());
        assertEquals(user.getLastName(), actualUser.getLastName());
        verify(userRepository).findByName(name); // verifica metodo foi executado
		verifyNoMoreInteractions(userRepository); // verifica se houve mais alguma interação
    }

    @Test
	public void emptyNameThrowsError(){ // manipulação de erros
		Exception error = assertThrows(Exception.class, ()-> userService.save(null));

		assertEquals(error.getMessage(), "Novo usuário deve ser enviado no corpo da requsição");
		assertEquals(error.getCause(), null);
	}
}
```

#### Testes de Integração
```java
package com.restapi.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.restapi.model.entity.User;

@SpringBootTest(webEnvironment = SprinbBootTest.WebEnvironment.RANDOM_PORT)
public UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public postUser(){
        String username = "Marlon";
        Long id = 1L;
        User newUser = new User();
        newUser.setName(username);
        // requisição POST, com corpo de newUser e retorno do tipo User
        User user = restTemplate.postForObjetc("/user/post", newUser, User.class);
        assertNotNull(user);
        assertEquals(username, user.getName());
        assertEquals(id, user.getId());
    }
}
```