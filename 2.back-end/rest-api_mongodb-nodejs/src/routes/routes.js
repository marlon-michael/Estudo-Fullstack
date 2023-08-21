const express = require('express')

const taskRouter = require('./taskRoutes')
const userRouter = require('./userRoutes')


const router = express.Router()
router.use('/user', userRouter)
router.use('/task', taskRouter)

module.exports = router
