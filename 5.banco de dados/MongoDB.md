# MongoDB

### Formas de manipular um banco de dados mongo

- MongoDB Compass - Atlas com interface de usuario
- Mongo Shell - Atlas em CLI (command line interface)
    ```console
    > mongosh <mongo-string> # default: mongo://127.0.0.1:27017/database-name
    ```

### Comandos em terminal
- listar bancos de dados criados
    ```console
    show databases
    ```
- criando um novo banco (a criação só acontece após a inserção do primeiro documento)
    ```console
    # use <database>
    use mydatabase
    ```
- criando um nova coleção
    ```console
    # coleção limitada ao valor de memoria de 1000 bytes
    # e número maximo de documentos de 100
    # e sem auto indexação do campo _id
    
    cb.createCollection( <collection>, {capped: true, size: 1000, max: 100}, {autoIndexId: false}) 
    ```
- deletando coleção
    ```console
    db. <collection> .drop()
    ```
- formas de seleção da coleção
    ```console
    db. <collections>
    -----------------
    db.getCollections("<collection>")
    ```
- inserindo documentos
    ```console
    # db. <collection> .insertOne({key:"value"})
    db.users.insertOne({name:"Max", age: 19}) 
    ```
- adicionar indices
    ```console
    # db.<collection>.createIndex({ <campo> : <1 (crescente) ou -1 (decrescente)> }) # adiciona indice para acelerar buscas usando <campo>
    db.users.createIndex({name: 1})

    # db.<collection>.getIndexes() # lista indices
    # db.<collection>.dropIndex("nome do indice") # deleta indice pelo nome
    ```
- listar documentos
    ```console
    # db.<collection>.find()
    db.users.find() # retorna todos os documentos
    ```
- buscas e filtros
    - para buscar um campo com valor especifico, passa-se um objeto dentro do metodo find, informando campo e valor esperado:
    ```console
    db.users.find({ <campo> : <valor de busca>, <demais campos para busca> })
    ```
    ```console
    # retorna documentos onde nome é igual a "max" e idade maior que 18
    db.users.find({name: "max", age: gt(18) }) 
    ```
    - operadores de busca, filtração e condição
        ```console
        # {$ne: <valor> } # retorna tudo que não for igual a <valor>
        db.<collection>.find({name: {$ne: "Max"}})

        # {$not: <valor> } # retorna tudo que não for igual a <valor>
        db.<collection>.find({name: {$not: "Max"}})

        # { <campo>: {$exists: <false ou true> }, { $set{ <campo> : "valor"} } # se existir (true) ou não existir (false) <campo> , define o <campo> como "valor"
        db.<collection>.find({role: {$exists: false}, { $set{role: "unemployed"} })

        # {$in: [ <valor1>, <valor2>} } # retorna tudo que tiver valor definido igual a <valor1> ou <valor2>
        db.<collection>.find({name: {$in: ["Max", "Maxwel"]}})

        # {$nin: [ <valor1>, <valor2>} } # retorna tudo que tiver valor definido diferente de <valor1> e <valor2>
        db.<collection>.find({name: {$nin: ["Max", "Maxwel"]}})

        # {$gt: <numero> } # maior do que <numero>
        # {$lt: <numero> } # menor do que <numero>
        # {$gte: <numero> } # maior ou igual a <numero>
        # {$lte: <numero> } # menor oi igual a <numero>
        db.<collection>.find({ age: {$gt: 18 } })
        
        # COMBINANDO OPERADORES

        # {$gt: <numero>, $lt <outro numero> } # retorna tudo que for maior que <numero> e menor que <outro numero>
        db.<collection>.find({ age: {$gt: 18, $lt:60 } })

        # { $and: [ {condição 1}, {condição 2} ] } # retorna tudo que atender todas as condições dentro do array
        # { $or: [ {condição 1}, {condição 2} ] } # retorna tudo que atender uma das condições dentro do array
        # { $nor: [ {condição 1}, {condição 2} ] } # retorna tudo que não atender nenhuma das condições dentro do array
        db.<collection>.find({ $and: [ {age: {$gt: 18, $lt:60 }}, {name: "Max" } ] })
        ```
- ordenar documentos
    ```console
    # db.<collection>.find().order({ <campo de ordenação> : < 1 (crescente) ou -1 (decrescente) > })
    db.users.find().sort({name:1}) # ordenado por nome crescente
    db.users.find().sort({name:-1}) # ordenado por nome decrescente

    # FILTRAÇÃO DOS CAMPOS

    # ordenado por nome em ordem crescente
    # retornando o campo nome de cada documento e removendo o campo _id
    db.users.find().sort({name:1}, {_id: false, name:true})
    
    ```
- limitar quantidade de documentos
    ```console
    # db.<collection>.find().limit( <número de documentos> )
    db.users.find().limit(5) # 5 primeiros documentos
    ```
- formatando o retorno no CLI
    ```console
    db. <colletion> .find().pretty()
    ```
- procurando por documentos
    ```console
    # db. <collection> .findOne({key:"value"})
    db.users.findOne({name:"Max"}) 
    ```
- atualizando documentos
    ```console
    # db. <collection> .updateOne({key:"value"}, {key: "newValue"})
    db.users.updateOne({name:"Max"}, {name: "Maxx"}) 
    ```
- adicionando e removendo campos de documentos
    ```console
    # db. <collection> .updateOne({key:"value"}, {key: "newValue"})

    # definindo campo "role" como "developer" onde user tem _id = 0
    db.users.updateOne({_id: 0}, {$set {role: "developer"} })

    # removendo campo "role" onde user tem _id = 0
    db.users.updateOne({_id: 0}, {$unset {role: ""} })
    ```
- deletando documentos
    ```console
    # db. <collection> .deleteOne({key:"value"})
    db.users.deleteOne({name:"Max"})
    ```


