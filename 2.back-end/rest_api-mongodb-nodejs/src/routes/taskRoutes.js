const express = require('express')
const taskModel = require('../model/tasksModel.js')


const taskRouter = express.Router()

taskRouter.get('/get', async (req, res) => {
    try {
        await taskModel.find()
        .then(tasks => res.status(200).json(tasks))
    }catch(error){
        res.status(400).json(error.message)
    }
})

module.exports = taskRouter
