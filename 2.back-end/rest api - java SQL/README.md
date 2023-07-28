# Spring boot API | Java + MySQL | MVC


### requerimentos
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

### configuração e criação
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

- IntelliJ
    - instalação das dependencias maven

### expondo rotas
- crie uma novo pacote controller: src/main/java/com/[project]/controller/
- crie um arquivo HelloController.java: HelloController.java
    ```java
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController // anotação para definição do controller
    public class HelloController{
        @GetMapping("/hello") // anotação para exposição da rota com requisição GET
        public String hello(){
            return "Hello Spring"
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

### requisição: Postman (UI), curl (CLI)
- fazendo requisição GET na URL localhost:8080/hello
    ```html
    Hello Spring
    ```

### models, views e controllers
- model
    - entity
    - DTO (data transfer object): camada de segurança evitando de expor dados da entity ao usuário
- view
    - repository
    - service
- controller
    - CRUD completo

    