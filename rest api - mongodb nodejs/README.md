# Como construir uma API com Node e MongoDB

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
        DATABASE_URL = mongodb://127.0.0.1:27017/nomedobanco
        ```
    
### desenvolvimento
- conectando Node com o banco de dados MongoDB
    - arquivo principal (index.js)
    ```javascript
    const mongo = require('mongoose') // importando mongoose
    require('dotenv').config // importando variaveis de ambiente

    const mongoString = process.env.DATABASE_URL // definindo String URL para conexão com MongoDB
    const database = mongo.connection // instancia do banco de dados
    database.on('error', (error) => {
        console.log(error) // em caso de erro, será informado no console 
    })
    database.once('connected', () => {
        console.log("database connected") // uma vez conectado ao banco de dados, será informado no console
    })
    ```
    - arquivo de modelos/schemas/entities (./models/models.js)
    ```javascript
    const mongo = require('mongoose')

    const dataSchema = new mongo.Schema({
        id: {required: true, type: Number}, 
        name: { required: true, type: String}
    })
    module.exports = mongo.model('Data', dataSchema)
    ```
- expondo API
    - arquivo principal (index.js)
    ```javascript
    const express = require('express') // importa o modulo express

    const app = express() // instancia o express
    app.use(express.json()) // define JSON como formato para troca de dados
    app.use('/', routes) // define a entrada da API junto das rotas
    app.listen(3000, () => {
        console.log('server started at port [ 3000 ]') // expõe a API na porta 3000
    })
    ```
    - arquivo de rotas (routes/routes.js)
    ```javascript
    const express = require('express') // importa mongoose
    const Model = require('../model/model.js') // importa os modelos utilizados no banco de dados
    
    const router = express.Router() // instancia a classe de rotas do express
    router.get('/get', async (req, res) => { // retorna todos os objetos (função asincrona)
        try{
            const data = await Model.find() // aguarda pela busca de todos os objetos
            res.json(data) // retorna dados
        }
        catch(error){
            res.status(400).json({message: error.message}) // retorna menssagem de erro em caso de falha na busca
        }
    })
    router.get('/get/:id', async (req, res) => { // retorna objeto por id (função asincrona)
        try{
            const data = await Model.findById(req.params.id) //aguarda pela busca do objeto
            res.status(200).json(data) // retorna objeto
        }
        catch(error){
            res.status(400).json({message: error.message}) // retorna menssagem de erro em caso de falha na busca
        }
    })
    router.post('/post', async (req, res) => { // cria dados (função asincrona)
        // guarda dados do modelo informado
        const data = Model({
            id: req.body.id, name: req.body.name
        })
        try{
            // tenta salvar dados no banco e os retorna
            const savingData = await data.save() // aguarda pela inserção do objeto
            res.status(200).json(savingData) // retorna objeto salvo
        }catch(error){
            res.status(400).json({message: error.message}) // retorna menssagem de erro em caso de falha na busca
        }
    })
    router.patch('/update/:id', (req, res) => { // atualiza dados por id
        try{
            const filter = {_id: req.params.id} // parametro e valor de busca
            const updatedData = req.body // parametros e valores a serem atualizados
            const options = {new: true} // ativa retorno do objeto atualizado
            // aguarda a busca e atualização do banco
            const result = await Model.findOneAndUpdate(
                filter, updatedData, options
            )
            res.status(200).send(result)
        }
        catch(error){
            res.status(400).json({message: error.message})
        }
    })
    router.delete('/delete/:id', (req, res) => { // deleta dados por id
        try{
            const data = await Model.findByIdAndDelete(req.params.id) // aguarda a busca e esclusão do objeto pelo banco
            res.status(200).send(`Object [${data.id}] has been deleted`) // retorna menssagem de exclusão
        }
        catch(error){
            res.status(400).json({message: error.message})
        }
    })

    module.exports = router // exporta as rotas definidas
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