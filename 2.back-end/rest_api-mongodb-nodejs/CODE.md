# Como desenvolver uma API com Node e MongoDB

## indices
- [conexão com MongoDB](#conectando-node-com-o-banco-de-dados-mongodb)  
- [expondo API](#expondo-api)
- [rotas e REST (CRUD)](#rotas-da-api-rest-crud)
- [paginação](#paginação-das-requisições)
- [contador/auto incrementador](#contador-auto-incrementador)
- [definindo acesso externo a API](#acesso-cors)

### conectando Node com o banco de dados MongoDB
- arquivo principal (index.js)
    ```javascript
    const mongo = require('mongoose') // importando mongoose
    require('dotenv').config // importando variaveis de ambiente

    // define as variaveis de ambiente
    const MONGO_URL = process.env.DATABASE_URL
    
    mongo.connect(MONGO_URL)
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
        id: {required: true, type: Number, unique: true}, // tipo númerico, unico, obrigatorio
        name: { required: true, type: String}, // tipo textual obrigatorio,
        friends: {required: false, type: [String]} // tipo array de strings, não obrigatorio
        accountType: {required: true, type: String, enum: ['busines','user']} // tipo texto, com valores predefinidos de 'busines' e 'user', obrigatorio
        deleted: {required: true, type: Boolean, default: false} // tipo boolean, com valor inicial: falso, obrigatorio
    })
    module.exports = mongo.model('Data', dataSchema)
    ```
### expondo API
- arquivo principal (index.js)
    ```javascript
    const express = require('express') // importa o modulo express

    const app = express() // instancia o express

    // define as variaveis de ambiente
    const SERVER_PORT = process.env.SERVER_PORT

    // USE APENAS UM FORMATO [JSON, TEXT]
    app.use(express.json()) // define JSON como formato para troca de dados
    // app.use(express.text()) // define texto como formato para troca de dados

    app.use('/', routes) // define a entrada da API junto das rotas
    app.listen(SERVER_PORT, () => {
        console.log('server started at port [ '+SERVER_PORT+' ]') // expõe a API na porta configurada
    })
    ```
### rotas da API REST (CRUD)
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
        if (!req.body.name) return res.status(400).json({message: 'user cannot be null'}) // retorna menssagem se dados estiverem em branco
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
    router.patch('/update/:id', async (req, res) => { // atualiza dados por id
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
    router.delete('/delete/:id', async (req, res) => { // deleta dados por id
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
### paginação das requisições
- uma das formas de diminuir o consumo de memoria da aplicação é divindo as requisições para termos retornos menores
    ```javascript
    const limit = 20 // objetos por pagina

    userRouter.get('/page/:page?', async (req, res) => { // atributo de rota opcional " :page? "
        let page = 1
        if (req.params.page && req.params.page > 0) page = req.params.page // se atributo page existir, atribui a variavel page
        try{
            const _res = await userModel.find() // requisição completa
            .limit(limit) // limita objetos por pagina
            .skip((page-1)* limit) // seleciona intervalo de retorno
            .exec() // executa requisição

            const count = await userModel.countDocuments() // guarda numero total de documentos no banco

            res.status(200).json({
                res: _res, // retorna o intervalo selecionado
                pageCount: Math.ceil(count / limit), // calcula quantas paginas existem
                page: page // retorna pagina atual
            })
        }catch(error){
            res.status(400).json({message: error.message})
        }
    })
    ```
### rotas encadeadas - nested routes
- cria um conjunto de rotas
    - userRoutes.js
        ```javascript
        const express = require('express')
        
        const userRouter = express.Router()
        userRouter.get('/get', ()=>{})

        module.exports = userRouter
        ```
    - routes.js
        ```javascript
        const express = require('express')
        userRouter = require('./userRouter.js')

        const router = express.Router()

        router.use('user', userRouter)
        ```
    - rota final
        > http://website.com/user/get
### contador auto incrementador
- como fazer um contador em MongoDB
    ```javascript
    const mongo = require('mongoose')

    const counterSchema = new mongo.Schema({ // criando estrutura do contador
        name: {required: true, type: String},
        value: {required: true, type: Number}
    })
    const schema = mongo.model('counter', counterSchema) // definindo modelo com o schema de contador

    async function nextSequence(name){ // função para buscar proximo valor da sequencia
        var sequence = await schema.findOneAndUpdate({name}, {$inc:{value:1}}, {new: true}) // buscar contador por nome
        return sequence.value // retorna proximo valor
    }

    async function addCounter(name){
        await schema.insertMany({name, value:0}) // insere no banco de dados um novo contador
    }

    module.exports = {
        schema,
        nextSequence,
        addCounter
    }
    ```
### acesso CORS
- permitindo acesso de aplicações externas (index.js)
    ```javascript
    // define as variaveis de ambiente
    const FRONTEND_URLS = process.env.FRONTEND_URLS

    // deve ser definida antes da exposição da API
    app.use((res,req,next) => {
    // permite que a URL informada acesse a API
    res.header('Access-Control-Allow-Origin', FRONTEND_URLS)
    req.header('Access-Control-Allow-Origin', FRONTEND_URLS)
    // permite que o cabeçalho Content-Type seja usado
    res.header("Access-Control-Allow-Headers", "Content-Type")
    req.header("Access-Control-Allow-Headers", "Content-Type")
    next() // continua fluxo de chamadas
    })
    ```
