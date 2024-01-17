# Como desenvolver uma API com Node e MongoDB

## indices
- [variaveis de ambiente](#variaveis-de-ambiente)
- [conexão com MongoDB](#conectando-node-com-o-banco-de-dados-mongodb)  
- [expondo API](#expondo-api)
- [rotas e REST (CRUD)](#rotas-da-api-rest-crud)
- [paginação](#paginação-das-requisições)
- [contador/auto incrementador](#contador-auto-incrementador)
- [definindo acesso externo a API](#acesso-cors)
- [autenticação JWT](#autenticação-jwt)
- [fazendo download de arquivos](#download-de-arquivos)
- [redirecionamento entre paginas](#redirecionar-a-outra-pagina)
- [reinderizando paginas dinamicas](#reinderizando-paginas-dinamicas)
- [reinderizando paginas html](#reinderização-de-paginas-html)
- [manipulação de arquivos](#manipulação-de-arquivos)
- [diretorios, caminhos e compatibilidade](#diretorios-e-caminhos)


### variaveis de ambiente

- configuração do dotenv
```javascript
require('dotenv').config() // importando variaveis de ambiente

// define as variaveis de ambiente
const MONGO_URL = process.env.DATABASE_URL
const FRONTEND_URLS = process.env.FRONTEND_URLS
const SERVER_PORT = process.env.SERVER_PORT
```
- arquivo de variaveis de ambiente (.env)
    - não se esqueça de adicinar o arquivo .env em .gitignore para não compartilhar chaves privadas
```json
DATABASE_URL = mongodb://127.0.0.1:27017/restapi
FRONTEND_URLS = 'http://localhost:5173'
SERVER_PORT = 3000
```

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
const express = require('express') // importa express
const Model = require('../model/model.js') // importa os modelos utilizados no banco de dados

const router = express.Router() // instancia a classe de rotas do express

// executa metodo para requisições com parametro id
router.params('id', (req, res, next, id)) {
    console.log('este trecho será executado após qualquer rota que possua o parametro id')
    next() // após a execução do metodo "next" o método continua normalmente
}

// executa metodo para todas as requisições
router.use(log_URL)
function log_URL(req, res, next){
    console.log(req.originalUrl) // faz log das URLs acessadas
    next() // continua execução da proximachamada
}

// encadeia multiplas funções log_URL -> log_URL -> requisição principal
router.get('/funcoes_encadeadas', log_URL, log_URL, (res, res)){
    console.log('a função log_URL será executada para esta rota')
}

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

// atributo de rota obrigatório " :page "
// atributo de rota opcional " :page? "
userRouter.get('/page/:page?', async (req, res) => {
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

### autenticação JWT

- dependencias
    - node
        - cookie-parser
        - jasonwebtoken
        - dotenv
        - express
        - cors (optional)

- JWT é um tipo de autenticação baseada em token encriptados contendo os dados trafegados, garantindo que quem encriptou é de fato quem acreditamos ser, usados para autenticar o usuario nas requisições web. Sua geração e guarda é feita no lado do usuario, portanto deve ser evitado o trafego de senhas e chaves privadas através do JWT uma vez que a encriptação serve para garantir a identidade e não para impedir a leitura.

- A autenticação será feita por dois tokens diferentes, um para o usuário fazer a navegação, que dura poucos minutos para assim, mesmo se roubada, não cause grandes problemas, e outra de longa duração usada apenas para gerar uma nova chave de acesso para a navegação.

- Configurações (cors) e uso
    - o front-end precisa estar nas origens de requisição dno servidor; 
    - o cabeçalho do front-end deve conter "credentials" com valor "include"

- gerar chaves secretas para tokens
```console
node
> require('crypto').randomBytes(64).toString('hex')
```
- arquivo de variaveis de ambiente (.env)
```json
ACCESS_TOKEN_SECRET = cee413baaaac685e7304b5fdafd0407d14bb7feee54d726dc04ade89436e5068aa0ca601a970f634964e0cbfcbad12d9b2550c0be91e72c8c4f33691a03715e8
REFRESH_TOKEN_SECRET = d45de3e1c3c0d179cea6cc0389d137ffe3f2f129dd5c6c312917f2a94488700b513372e4a0e91fd7ada9b5d4b3c5e1ff5d3b3e57d537fff409dc746fbe649a79
```

- arquivo de configuração do JWT e autenticação
```javascript
const express = require('express')
const app = express()
const router = express.Router()
const jwt = require('jasonwebtoken')
const cookieParser = require('cookie-parser')
require('dotenv').config()

const credentials = (req, res, next) => {
    const allowedOrigins = ['https://www.seusite.com', 'https://127.0.0.1:3000']
    const origin = req.headers.origin
    if (allowedOrigins.includes(origin)) res.header('Acess-Control-Allow-Credentials', true)
    next()
}

// cria tokens
const login = await (req, res) => {
    // o token de atualização fica salvo junto dos dados do usuario no lado do servidor, onde geramos um novo token de acesso com base nele
    const refreshToken = jwt.sign(
        {'email':'user@email.com'}, // informações publicas do usuario
        process.env.REFRESH_TOKEN_SECRET // chave do token de atualização
        {expiresIn: '1d'}, // tempo de expiração do token de atualização
    )

    db.update(user, {refreshToken: refreshToken}) // adiciona refresh token ao usuario no banco

    // o token de acesso é retornado para o usuario e ao javascript
    const acessToken = jwt.sign(
        {'email':'user@email.com'}, // informações publicas do usuario
        process.env.ACCESS_TOKEN_SECRET // chave do token de acesso
        {expiresIn: '60s'}, // tempo de expiração do token de acesso
    )

    // define o trafego do token de acesso apenas as requisições html, não permitindo o uso no javscript, alem de definir o tempo de armazenamento do token para 24h (1000ms x-> 60s -> 60m -> 24h)
    res.cookie('jwt', refreshToken, {HttpOnly: true, sameSite: 'None', secure: true, maxAge: 1000 * 60 * 60 * 24})
    res.json({acessToken})
}

// atualiza token de acesso (requisições em js) se o usuario tiver o refresh token (cookie)
const refreshToken = (req, res) => {
    const cookies = req.cookies
    if (!cookies?.jwt) return res.sendStatus(403) // se não encontrar o refresh token nos cookies retorna 403
    const refreshToken = cookies.jwt // armazena refresh token do usuário

    const user = db.find(user => user.refreshToken === refreshToken) // busca usuario com o refresh token enviado pelo o usuario

    if (!user) return res.sendStatus(403) // se não encontrar usuario retorna 403
    jwt.verify(
        refreshToken, 
        process.env.REFRESH_TOKEN_SECRET,
        (err, decoded) => {
            if (err || user.email !== decoded.email) return res.sendStatus(403) // se nome do usuario for diferente do nome de usuario do jwt ou gerar erro retorna 403
            const acessToken = jwt.sign(
                {'email':'user@email.com'}, // informações publicas do usuario
                process.env.ACCESS_TOKEN_SECRET // chave do token de acesso
                {expiresIn: '60s'}, // tempo de expiração do token de acesso
            )
            res.json({acessToken})
        }
    )
}

// verifica se o token de acesso é valido
const verificaJWT = (req, res, next) => {
    const authHeader = req.headers['authorization']
    if (!authHeader) return res.sendStatus(401)

    const token = authHeader.split(' ')[1]
    jwt.verify(
        token,
        process.env.ACESS_TOKEN_SECRET,
        (err, decoded) => {
            if (err) return res.sendStatus(403) // invalid token
            req.user = decoded.email
            next()
        }
    )
}

// remove o refresh token do servidor impedindo a aquisião de novos tokens de acesso
const logout = (req, res) => {
    const cookies = req.cookies
    if (!cookies?.jwt) return res.sendStatus(403) // se não encontrar o refresh token nos cookies retorna 403
    const refreshToken = cookies.jwt // armazena refresh token do usuário

    const user = db.find(user => user.refreshToken === refreshToken) // busca usuario com o refresh token enviado pelo o usuario

    if (!user) {
        res.clearCookie('jwt', {HttpOnly: true, sameSite: 'None', secure: true, maxAge: 1000 * 60 * 60 * 24}) // deleta cookie jwt
        res.sendStatus(204)
    }

    db.update(user, {refreshToken: null}) // remove refresh token do banco
    res.clearCookie('jwt', {HttpOnly: true, sameSite: 'None', secure: true, maxAge: 1000 * 60 * 60 * 24}) // deleta cookie jwt
    res.sendStatus(204)
}

// verifica se usuario está autenticado para acessar a pagina de usuarios
router.get('/user', verificaJWT, (res, res)){
    console.log('usuario foi autenticado neste metodo/requisição')
}

app.use(credentials) // habilita servidor a aceitar credenciais jwt
app.use(cookieParser) // habilita o express a interagir com cookies
app.use('/', require('./rootRouter'))

app.use(verificaJWT) //tudas as rotas após o uso do verificador JWT será verificado
app.use('/user', require('./userRouter'))

```

### download de arquivos

```javascript
app.get('/download', (req, res)) {
    res.download('/caminho_do/arquivo.txt')
}
```

### manipulação de arquivos

- #### Sincrono

```javascript
const fileSystem = require('fs')

// VERIFICA SE DIRETORIO EXISTE
if (fileSystem.existsSync('./caminho/do/diretorio')){
    // CODIGO
}

// CRIA NOVO DIRETORIO / PASTA
fileSystem.mkdir('./caminho/do/diretorio', (error)=>{
    if (error) throw error
})

// REMOVE DIRETORIO / PASTA
fileSystem.rmdir('./caminho/do/diretorio', (error)=>{
    if (error) throw error
})

// LEITURA DE ARQUIVOS
fileSystem.readFile('./caminho/arquivo', 'utf-8', (error, data) => {
    if (error) throw error

    // caso não seja especificado a codificação os dados do arquivo estarão em formato de buffer, sendo necessário convertelos para string posteriormente
    console.log(data)
})

// ESCRITA DE ARQUIVOS
fyleSystem.writeFile('./caminho/arquivo.txt', 'este texto será inserido dentro do arquivo', (error)=>{
    if (error) throw error
})

// ESCRITA EM ARQUIVOS EXISTENTES
fyleSystem.appendFile('./caminho/arquivo.txt', 'este texto será inserido dentro do arquivo novamente', (error)=>{
    if (error) throw error
})

// RENOMEAR ARQUIVOS
fyleSystem.rename('./caminho/arquivo.txt', './caminho/novo-nome.txt', (error)=>{
    if (error) throw error
})

// DELETAR ARQUIVOS
fyleSystem.unlink('./caminho/arquivo.txt', (error)=>{
    if (error) throw error
})
```

- #### Asincrono

```javascript
const asyncFileSystem = require('fs').promises

// LEITURA DE ARQUIVOS
const asyncReading = async () => {
    try{
        // caso não seja especificado a codificação os dados do arquivo estarão em formato de buffer, sendo necessário convertelos para string posteriormente
        const data = await asyncFileSystem.readFile('./caminho/arquivo', 'utf-8')
        console.log(data)
    }catch (error){
        console.error(error)
    }
}

asyncReading()
```
    possui os mesmos metodos da versão sincrona porem emitindo a função callback de erro 

- #### Streams
    - utilizado para manipulação arquivos grandes 
```javascript
const fs = require('fs')

// cria stream de leitura para arquivo-gigante, com enconding utf-8
const rs = fs.createReadStream('./pasta/arquivo-gigante', {encoding: 'utf-8'})

// cria stram de escrita para novo-arquivo-gigante
const ws = fs.createWriteStram('./pasta/novo-arquivo-gigante')

// lê dados de "arquivo-gigante" e escreve dentro de "novo-arquivo-gigante"
rs.on('data', (dataChunk)=>{
    ws.write(dataChunk)
})
// faz a mesma coisa que o método acima de forma mais eficiente
rs.pipe(ws) 
```

### diretorios e caminhos
- para evitarmos incompatibilidades na estrutura de caminhos entre diferentes sistemas operacionais, podemos utilizar o modulo "path"
```javascript
const path = require('path')

// __dirname representa o diretório atual, que será concatenas com pasta1, pasta2 e então o arquivo desejado

path.join(__dirname, 'pasta1', 'pasta2', 'arquivo')
// LINUX: ./pasta1/pasta2/arquivo
// WINDOWS: .\pasta1\pasta2\arquivo
```

### redirecionar a outra pagina
```javascript
app.get('/download', (req, res)) {
    res.redirect('/caminho_da/pagina')
}
```

### reinderizando paginas dinamicas
- requisitos
    - instalação de um gerenciador de paginas
        - > npm install ejs
- definição do motor de reinderização
```javascript
app.set('view engine', 'ejs')
```
- diretório das paginas
    - ./views/index.ejs
- reinderização das paginas
```javascript
app.get('/download', (req, res)) {
    res.render('index')
}
```

#### execução de código com EJS
- ./views/index.ejs
```html
<html>
    <body>
        <h1>o texto abaixo entre os sinais de porcentagem, igual, menor e maior será interpretado como linhascódigo</h1>

        <%= 1+1 %> # executa código retornando apenas o valor dasoma de 1+1
        <%= locals.message_from_node || "menssagem padrão" %> # mostra o valor armazenado na variavel
    </body>
</html>
```
- reinderização da pagina em node
```javascript
app.get('/download', (req, res)) {
    res.render('index', {message_from_node: 'está é a menssagem do node + ejs'})
}
```

### reinderização de paginas html
- pasta que colocaremos nossas paginas html, qualquer arquivo e e pasta nesse diretório será servido como pagina e caminho pelo express
    - ./public (pode mudar para nome que preferir)
```javascript
// definimos o caminho da pasta para o express
app.use(express.static('public'))

// habilitamos acesso do express ao corpo das paginas
app.use(express.urlencoded({extended:true})) 
```