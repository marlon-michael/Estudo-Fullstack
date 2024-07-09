# Spring boot API | Java + MySQL | MVC


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
        ...
        <artifactId>rabbitmq</artifactId>
        <!-- DEIXE PACKAGING COMENTADO NO LINUX | UTILIZE PACKAGING POM NO WINDOWS -->
        <packaging>pom</packaging>
        <version>0.0.1-SNAPSHOT</version>
        ...
        ```
        - dependencias
            - spring-boot-starter-amqp (interface de menssageria)
            - spring-boot-starter-web (bibliotecas web)
            - lombok (utilidades) 
        - plugins
            - spring-boot-maven-plugin

- IntelliJ
    - instalação das dependencias maven
    ```console
    mvn clean install
    ```

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

### Model, view e controller
- model
    - entity
    - DTO (data transfer object): camada de segurança evitando de expor dados da entity ao usuário
- view
    - repository
    - service
- controller
    - CRUD completo

    