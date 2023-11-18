const express = require('express')
const mongo = require('mongoose')
const routes = require('./src/routes/routes')
require('dotenv').config()


const MONGO_URL = process.env.DATABASE_URL
const FRONTEND_URLS = process.env.FRONTEND_URLS
const SERVER_PORT = process.env.SERVER_PORT

mongo.connect(MONGO_URL)
const database = mongo.connection
database.on('error', error => console.log(error))
database.once('connected', () => console.log('>>> database connected'))

const app = express()
app.use((res,req,next) => {
    req.header('Access-Control-Allow-Origin', FRONTEND_URLS)
    req.header("Access-Control-Allow-Headers", "Content-Type")
    next()
})
app.use(express.json()) // app.use(express.text())
app.use('/', routes)
app.listen(SERVER_PORT, () => {
    console.log('>>> server started at port [ '+SERVER_PORT+' ]')
})
