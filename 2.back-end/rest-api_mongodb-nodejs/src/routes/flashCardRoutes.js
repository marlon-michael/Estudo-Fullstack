const express = require('express')
const flashCard = require('../model/flashCardModel')


flashCardRouter = express.Router()

flashCardRouter.get('/get', async (req, res) => {
    try{
        const search = await flashCard.find()
        res.status(200).json(search)
    }catch(error){
        res.status(400).json({message: error.message})
    }
})

flashCardRouter.post('/post', async (req, res) => {
    try{
        const card = flashCard(req.body)
        result = card.save()
        res.status(200).json(result)
    }catch(error){
        res.status(400).json({message: error.message})
    }
})


module.exports = flashCardRouter