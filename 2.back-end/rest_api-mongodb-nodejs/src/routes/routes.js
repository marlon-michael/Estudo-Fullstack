const express = require('express')

const userRouter = require('./userRoutes')
const taskRouter = require('./taskRoutes')
const flashCardRouter = require('./flashCardRoutes')


const router = express.Router()
router.use('/user', userRouter)
router.use('/task', taskRouter)
router.use('/card', flashCardRouter)

module.exports = router
