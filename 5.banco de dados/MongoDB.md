# MongoDB


### Formas de manipular um banco de dados mongo

- MongoDB Compass - Atlas com interface de usuario
- Mongo Shell - Atlas em CLI (command line interface)
    ```console
    > mongosh [mongo-string] # default: mongo://127.0.0.1:27017/database-name
    ```

### Comandos em terminal
- listar bancos de dados criados
    ```console
    show databases
    ```
- criando um novo banco (a criação só acontece após a inserção do primeiro documento)
    ```console
    use mydatabase # use [database-name]
    ```
- formas de seleção da coleção
    ```console
    db.[collections-name]
    ---------------------
    db.getCollections("collection-name")
    ```
- formatando a saida
    ```console
    db.[colletion-name].find().pretty()
    ```
- inserindo documentos
    ```console
    db.users.insertOne({name:"Max", age: 19}) # db.[collection].insertOne({key:"value"})
    ```
- listar documentos
    ```console
    db.users.find() # db.[collection].find()
    ```
- procurando por documentos
    ```console
    db.users.findOne({name:"Max"}) # db.[collection].findOne({key:"value"})
    ```
- atualizando documentos
    ```console
    db.users.updateOne({name:"Max"}, {name: "Maxx"}) # db.[collection].updateOne({key:"value"}, {key: "newValue"})
    ```
- deletando documentos
    ```console
    db.users.deleteOne({name:"Max"}) # db.[collection].deleteOne({key:"value"})
    ```

