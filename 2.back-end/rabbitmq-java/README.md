# Filas com RabbitMQ utilizando Java

## Requisitos

- Java 22 +
    - maven 3.9.6 + (gerenciador de dependencias)

- RabbitMQ 3.11
    - Docker

## Preparando o projeto

- Java / Maven

    ```console
    mvn clean install
    mvn spring-boot:run # depois de ter iniciado o rabbit mq
    ```

- RabbitMQ (Docker)

    ```console
    sudo docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:3.11-management
    ```