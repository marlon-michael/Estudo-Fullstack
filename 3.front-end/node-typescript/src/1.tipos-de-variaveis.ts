
// tipos de variaveis não podem ser substituidos
// tipos de variaveis: 
let any_var: any; //qualquer tipo pode ocupar essa variavel
let string_array_var: String[]; // array de String
let number_or_string_var: number | String; // numeros ou Strings
let number_var = 0; // apenas numeros


// tipagem de argumentos e retornos de funções
let sum_func = (x:number, y:number):number=>{return x+y}


// criando novos tipos de objetos e interfaces
type Triangle = {
    angle: number,
    type: "isosceles"|"scalene"|"obtuse" // valores predefinodos: isosceles, scalene, obtuse
}
type Circle = {
    radius: number,
    border: "bordered"|"unbordered" // valores predefinidos: bordered, unbordered
}
type Rect = {
    width?: number, // argumento opcional de tipo numerico
    height?: number // argumento opcional de tipo numerico
}

type GeometricFigure = Rect | Triangle | Circle; // valores predefinidos: Rect, Triangle, Circle

// instanciamento dos tipos criados
let rect: GeometricFigure = {
    width: 20,
}
