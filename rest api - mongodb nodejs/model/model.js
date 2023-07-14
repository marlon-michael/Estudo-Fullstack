const mongo = require('mongoose')


const userSchema = new mongo.Schema({
    name: {
        required: true,
        type: String
    }
})

module.exports = mongo.model('User', userSchema)