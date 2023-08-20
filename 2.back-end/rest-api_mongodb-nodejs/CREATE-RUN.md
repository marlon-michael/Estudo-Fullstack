# Criando e rodando a API

### requerimentos
- MongoDB Comunnity: 6.0.7
- Node jS: 18:12.1
    - mongoose: 7.3.4
    - express: 4.18.2
    - dotenv: 16.3.1
    - nodemon: 3.0.1 (opcional)

### configuração
- Node
    - instalação de bibliotecas
        - mongoose: interação com o mongoDB
        - express: disponibilizar API
        - nodemon: reinicia nossa apicação após qualquer alteração
        - dotenv: gerenciar nossas variaveis de ambiente
        ```console
        npm install mongoose express nodemon dotenv
        ```
    - configuração das variaveis de ambiente [arquivo .env]
    ```javascript
    DATABASE_URL = mongodb://127.0.0.1:27017/restapi
    SERVER_PORT = 3000
    FRONTEND_URLS = 'http://localhost:5173'
    ```

### rodando a aplicação
- MongoDB
    - iniciar o mongodb na pasta de dados do projeto
        ```console
        mongod --dbpath C:\pasta_do_projeto\dados
        ```

- Node JS
    - iniciar a aplicação com nodemon
        ```console
        npm start
        ```
    - iniciar a aplicação com npm
        ```console
        npm index.js
        ```