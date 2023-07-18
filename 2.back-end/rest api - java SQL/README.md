# Spring boot API | Java + MySQL


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
        - Lombok: diminui código utilizando notações

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
        - dependencias: Spring Web, Spring Data JPA, MySQL Driver, Lombok
    - configuração do spring boot: (src.main.resources.application.properties)
        ```json
        spring.datasource.url=jdbc:mysql://localhost:3306/[database-name] // string de conexão SQL
        spring.datasource.username=[database-username]
        spring.datasource.password=[database-pass]
        spring.datasource.driven-class-name=com.mysql.cj.jdbc.Driver
        spring.jpa.hibernate.ddl-auto=update // none / update / create / create-drop
        spring.jpa.show-sql=true
        ```

- IntelliJ
    - instalação das dependencias maven

### primeiro controller
- crie uma novo pacote controller: src/main/java/com/[project]/controller/
- crie um arquivo HelloController.java: HelloController.java
    ```java
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController // anotação controller
    public class HelloController{
        @GetMapping("/hello") // anotação para exposição da rota com requisição GET
        public String hello(){
            return "Hello Spring"
        }
    }
    ```

### Hello Spring - rodando o projeto
- Intellij
    - inicie o projeto pela classe [project-name] ( src/main/java/com/[project-name]/[project-name]Application.java )

### requisição: Postman (UI), curl (CLI)
- fazendo requisição GET em localhost:8080/hello
    ```json
    Hello Spring
    ```



    