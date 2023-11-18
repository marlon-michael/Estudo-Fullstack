const mongo = require('mongoose')


const userSchema = new mongo.Schema({
    name: {required: true, type: String},
    // email: {required: true, type: String, unique: true},
    // password: {required: true, type: String},
    // born_date: {required: true, type: String}
})

module.exports = mongo.model('User', userSchema)