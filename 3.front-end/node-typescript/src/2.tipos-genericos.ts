
// tipo generico de Box
type Box<Box_Type = any> = {
    contents? : Box_Type[] // Box comtem um array do tipo informado ou de qualquer tipo (any)
    contentLength: () => number; // função que deve retornar um numero
}

// Box de tipo qualquer (any)
let box: Box = {
    contents: [],
    contentLength: () => box.contents!.length // definição da funçãoque retorna o numero de elementos em Box.contents (forçado a retornar algum valor)
}
box.contents?.push(12)
box.contents?.push("asd")
box.contentLength() // output: 2

// Box com tipo numérico
let box2: Box<number> = {
    contents: [],
    contentLength: () => box2.contents?.length ?? 0 // função que retorna o numero de elementos em contsnts ou o número zero
}
box2.contents?.push(12)
// box2.contents?.push("asd")        // output: ERROR: não é possivel assimilar String para Box de valor numerico

// função generica
function sum<Func_Type>(arg: Func_Type): Func_Type{
    return arg
}

// uso de função generica
let sum_return: string = sum("123");