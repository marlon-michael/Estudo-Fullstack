

function methods(obj, proto = true, filter = false){
    let res
    if (proto) res = Object.getOwnPropertyNames(obj.prototype)
    else res = Object.getOwnPropertyNames(obj)
    if (filter){
        res = res.filter((property) => {
            return typeof(obj[property]) == 'function'
        })
    }
    console.log(res)
}



module.exports = {
    methods
}