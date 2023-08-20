# Como desenvolver uma API com Node e MongoDB


### desenvolvimento
- conectando Node com o banco de dados MongoDB
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

    // define as variaveis de ambiente
    const FRONTEND_URLS = process.env.FRONTEND_URLS
    const SERVER_PORT = process.env.SERVER_PORT

    app.use((res,req,next) => {
        // permite que a URL informada acesse a API
        res.header('Access-Control-Allow-Origin', FRONTEND_URLS)
        req.header('Access-Control-Allow-Origin', FRONTEND_URLS)
        // permite que o cabeçalho Content-Type seja usado
        res.header("Access-Control-Allow-Headers", "Content-Type")
        req.header("Access-Control-Allow-Headers", "Content-Type")
        next() // continua fluxo de chamadas
    })

    // USE APENAS UM FORMATO [JSON, TEXT]
    app.use(express.json()) // define JSON como formato para troca de dados
    // app.use(express.text()) // define texto como formato para troca de dados

    app.use('/', routes) // define a entrada da API junto das rotas
    app.listen(SERVER_PORT, () => {
        console.log('server started at port [ '+SERVER_PORT+' ]') // expõe a API na porta configurada
    })
    ```
    - arquivo de rotas (routes/routes.js)
    ```javascript
    const express = require('express') // importa mongoose
    const Model = require('../model/model.js') // importa os modelos utilizados no banco de dados
    
    const router = express.Router() // instancia a classe de rotas do express
    router.get('/get', async (req, res) => { // retorna todos os objetos (função asincrona)
        res.set('Access-Control-Allow-Origin', '*') // quem pode fazer requisição
        try{
            const data = await Model.find() // aguarda pela busca de todos os objetos
            res.json(data) // retorna dados
        }
        catch(error){
            res.status(400).json({message: error.message}) // retorna menssagem de erro em caso de falha na busca
        }
    })
    router.get('/get/:id', async (req, res) => { // retorna objeto por id (função asincrona)
        res.set('Access-Control-Allow-Origin', '*') // quem pode fazer requisição
        try{
            const data = await Model.findById(req.params.id) //aguarda pela busca do objeto
            res.status(200).json(data) // retorna objeto
        }
        catch(error){
            res.status(400).json({message: error.message}) // retorna menssagem de erro em caso de falha na busca
        }
    })
    router.post('/post', async (req, res) => { // cria dados (função asincrona)
        res.set('Access-Control-Allow-Origin', '*') // quem pode fazer requisição
        if (typeof(req.body)=="string") req.body = JSON.parse(req.body) // convertendo String/TEXTO para JSON
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
        if (typeof(req.body)=="string") req.body = JSON.parse(req.body) // convertendo String/TEXTO para JSON
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
