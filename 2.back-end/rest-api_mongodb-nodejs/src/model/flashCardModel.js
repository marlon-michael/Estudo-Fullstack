const mongo = require('mongoose')


const cardSchema = new mongo.Schema({
    deck:{required:true, type:String},
    question:{required:true, type:String},
    answer:{required:true, type:[String]},
    options:{required:false, type:[String]},
    answer_mode:{required:true, type:String, enum: ['type', 'choice', 'multi-choice'], default: 'type'},
    frequency:{required:true, type:Number, default: 1},
})

module.exports = mongo.model('FlashCard', cardSchema)