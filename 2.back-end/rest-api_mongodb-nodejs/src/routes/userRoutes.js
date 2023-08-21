const express = require('express')
const userModel = require('../model/userModel.js')
const counterModel = require('../model/counterModel.js')


const userRouter = express.Router()

async function nextSeq(sequenceName){
    var sequence = await counterModel.findOneAndUpdate({_id: sequenceName}, {$inc:{sequence_value:1}}, {new: true})
    return sequence.sequence_value
}

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
    try{
        savingUser = await nextSeq('user_id').then(id => {
            userModel.insertMany({
                id: id,
                name: req.body.name,
                email: req.body.email,
                password: cript(req.body.password),
                bornDate: date(req.body.born_date)
            })
        })
        res.status(200).json(savingUser)
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