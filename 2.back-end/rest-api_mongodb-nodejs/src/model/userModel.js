const mongo = require('mongoose')


const userSchema = new mongo.Schema({
    id: {required: true, type: String, unique: true},
    born_date: {required: true, type: String},
    name: {required: true, type: String},
    email: {required: true, type: String, unique: true},
    password: {required: true, type: String}
})

module.exports = mongo.model('User', userSchema)