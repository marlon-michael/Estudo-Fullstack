# Typescript

### o que é typescript
typescript é uma linguagem fortemente tipada que é compilada para javascript com o objetivo de minimizar erros de tipagem dinamica ou fraca quanto a liberdade de atribuir um valor inesperado a uma variavel. Assim minimizando erros em tempo de execução do programa e facilitando a codificação do mesmo uma vez que todos os atributos e tipos de cada objeto já foram definidos anteriormente a utilização.

### como usar typescript

- operadores
```javascript
let OBJ = {item: undefined}
let A = 1;
let B = 0;
let C;

B = <number><unknown>"10" // casting de <string> para <number>
C = A??B; // A é undefined/null ? então B
C = A||B;  // A contem valor não falso (0, "", null) ? senão retorna B
console.log(C);
console.log(OBJ?.item) // OBJ pode ser undefined, mas queremos acessa-lo mesmo assim
```

- tipagem dinamica
```javascript
let variavel = 1 // inicializado variavel númerica
variavel = "texto" // Erro: String não é atribuivel a variavel de tipo númerico
```
- sem tipagem
```javascript
let variavel // variavel não inicializada de tipo qualquer (any)
variavel = 1 // funciona
variavel = "texto" // funciona
```
- tipos de variaveis
```javascript
let num:number = 1 // numerico
let text:String = "texto" // texto
let array:any[] = [1, "texto"] // array de qualquer tipo
let stringArray:String[] = ['texto','texto2'] // array de texto
let obj:object = {id: 1} // objeto generico
let funcao: Function = (a:number,b:number):number => a+b // função tipada
```
- criação de tipos
```javascript
type myObject = { // sintaxe da declaração de um novo tipo
    id: number
    name:string
}

let myObj:myObject = {id: 1, name: "User"} // declaração do tipo criado
```
- tipos genericos
```javascript
type Box<type = any> = {
    values:type[] // tipagem informada peo usuario
}

let box:Box<number> = {values: [1,2,3]} // declaração do tipo criado
```
- funções genericas
```javascript
// função generica
function sum<Func_Type>(arg: Func_Type): Func_Type{
    return arg
}
// uso de função generica
let sum_return: string = sum("123");
```
- usos e funções especiais
```javascript
type Special = {
    values:any[]
    optionalValue?:any // valor opcional ao colocar " ? " ao final do nome da variavel
    numberOrString?: String | Number // valor pode ser String ou Number
    predefValues?: "ON"|"OFF" // valores predefinidos: ON, OFF
    length: () => number // função que retorna um número
}

let variavel:Special = {values:[1,2,3], length: ()=> {
    // força( ! ) buscar valor de " .length " independente do valor de " variavel.values "
    return variavel.values!.length
}}

let variavel2:Special = {values:[1,2,3], length: ()=> {
    // se variavel.values exestir( ? ), busca valor de " .length " senão( ?? ) retorna 0
    return variavel2.values?.length ?? 0
}}
```
- classes e interfaces
```javascript
interface Pingable{
    ping(): void
}
// Classe Point que implementa Pingable
class Point implements Pingable{
    private _x: number = 0
    private _y: number = 0
    protected readonly class_name: string = "Class name" // valor final
    //contrutor da classe
    constructor(x?: number, y?: number, class_name?: string){
        this._x = x??0
        this._y = y??0
        this.class_name = class_name??"Class Point()" // pode editar valor readonly aqui
    }

    get x(): number {return this._x}
    get y(): number {return this._y}
    set x(x: number) {this._x = x}
    set y(y: number) {this._y = y}
    
    // metodo estatico. pode ser usado sem a instancia da classe
    static help(){return "atr: _x, _y, _z / methods: typeOf, position, ping, getters and setters"}
    typeOf = () => {return this.class_name} // retorna o tipo da classe
    position = () => {return [this._x, this._y]} // retorna valores de atributo X e Y
    ping(): void {console.log("ping")}
}
// classe DDDPoint que herda de Point
class DDDPoint extends Point{
    private _z: number = 0

    // construtor de DDDPoint
    constructor(x?: number, y?: number, z?: number, class_name?: string){
        super(x, y, class_name)
        this._z = z??0
    }

    typeOf = () => {return super.typeOf()} // retorna tipo da classe
    position = () => {
        let arr = super.position()
        arr.push(this._z) // adiciona valor ao array
        return arr
    } // retorna valores de atributos X, Y, Z
}
```