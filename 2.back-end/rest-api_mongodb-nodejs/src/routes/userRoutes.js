const express = require('express')
const userModel = require('../model/userModel.js')


const userRouter = express.Router()

const limit = 20
userRouter.get('/page/:page?', async (req, res) => {
    let page = 1
    if (req.params.page && req.params.page > 0) page = req.params.page
    try{
        const _res = await userModel.find()
        .limit(limit)
        .skip((page-1)* limit)
        .exec()

        const count = await userModel.countDocuments()

        res.status(200).json({
            res: _res,
            pageCount: Math.ceil(count / limit),
            page: page
        })
    }catch(error){
        res.status(400).json({message: error.message})
    }
})

userRouter.get('/get', async (req, res) => {
    try{
        const users = await userModel.find()
        res.status(200).json(users)
    }
    catch(error){
        res.status(400).json({message: error.message})
    }
})

userRouter.get('/get/:id', async (req, res) => {
    try{
        const user = await userModel.findById(req.params.id)
        res.status(200).json(user)
    }
    catch(error){
        res.status(400).json({message: error.message})
    }
})

const cript = (pass) => pass // fazer criptografia de senhas em sha256 ou melhor
const date = (date) => date // encontrar biblioteca de datas

userRouter.post('/post', async (req, res) => {
    if (!req.body.name) return res.status(400).json({message: 'usuario não pode ter campos em branco'})
    const data = userModel({name: req.body.name})
    try{
        const savingData = await data.save()
        res.status(200).json(savingData)
    }
    catch(error){
        res.status(400).json({message: error.message})
    }
})

userRouter.patch('/patch/:id', async (req, res) => {
    try{
        const filter = {_id: req.params.id}
        const updatedUser = req.body
        const options = {new: true}
        const result = await userModel.findOneAndUpdate(
            filter, updatedUser, options
        )
        res.status(200).send(result)
    }
    catch(error){
        res.status(400).json({message: error.message})
    }
})

userRouter.delete('/delete/:id', async (req, res) => {
    try{
        const user = await userModel.findByIdAndDelete(req.params.id)
        res.status(200).send(`User [${user.name}] has been deleted`)
    }
    catch(error){
        res.status(400).json({message: error.message})
    }
})

module.exports = userRouter