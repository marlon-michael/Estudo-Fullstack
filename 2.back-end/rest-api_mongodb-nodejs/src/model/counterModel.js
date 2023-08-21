const mongo = require('mongoose')


const counterSchema = new mongo.Schema({
    _id: {required: true, type: String},
    sequence_value: {required: true, type: Number}
})

module.exports = mongo.model('counter', counterSchema)