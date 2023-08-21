const mongo = require('mongoose')


const taskSchema = new mongo.Schema({
    id: {required: true, type: String, unique: true},
    title: {required: true, type: String},
    body: {required: false, type:String},
    priority: {required: true, type: Number},
    collaborators_id: {required: true, type: Int32Array},
    isComplete: {required: true, type: Boolean}
})

module.exports = mongo.model('Task', taskSchema)