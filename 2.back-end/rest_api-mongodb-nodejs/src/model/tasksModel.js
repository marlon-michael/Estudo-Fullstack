const mongo = require('mongoose')


const taskSchema = new mongo.Schema({
    id: {required: true, type: String, unique: true},
    title: {required: true, type: String},
    body: {required: false, type:String},
    priority: {required: true, type: Number},
    owner: {required: true, type: String},
    collaborators_id: {required: false, type: [String]},
    completed: {required: true, type: Boolean}
})

module.exports = mongo.model('Task', taskSchema)