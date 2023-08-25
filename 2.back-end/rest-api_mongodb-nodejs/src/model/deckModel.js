const mongo = require('mongoose')


const deckSchema = mongo.Schema({
    name:{required:true, type:String},
    children:{type:[String] || [deckSchema]},
    owner:{required:true, type:String},
    public:{required:true, type:Boolean}
})


module.exports = mongo.model('Deck', deckSchema)