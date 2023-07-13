# Como construir uma API com Node e MongoDB

### requerimentos
- MongoDB Comunnity: 6.0.7
- Node jS: 18:12.1

### configuração
- MongoDB
    - iniciar o mongodb na pasta de dados do projeto
        ```console
        mongod --dbpath C:\pasta_do_projeto\dados
        ```

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
        DATABASE_URL = mongodb://127.0.0.1:27017/
        ```