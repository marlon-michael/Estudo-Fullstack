const express = require('express')


const router = express.Router()

router.post('/post', (req, res) => {
    res.send('Post API')
})

router.get('/getAll', (req, res) => {
    res.send('Get all API')
})

router.get('/getById/:id', (req, res) => {
    res.send('Get by id API')
})

router.patch('/post/:id', (req, res) => {
    res.send('Update by id API')
})

router.delete('/delete/:id', (req, res) => {
    res.send('Delete by id API')
})

module.exports = router
