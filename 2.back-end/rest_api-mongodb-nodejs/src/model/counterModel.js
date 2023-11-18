const mongo = require('mongoose')


const counterSchema = new mongo.Schema({
    name: {required: true, type: String},
    value: {required: true, type: Number}
})
const schema = mongo.model('counter', counterSchema)

async function nextSequence(name){
    var sequence = await schema.findOneAndUpdate({name}, {$inc:{value:1}}, {new: true})
    return sequence.value
}

async function addCounter(name){
    await schema.insertMany({name, value:0})
}

module.exports = {
    schema,
    nextSequence,
    addCounter
}