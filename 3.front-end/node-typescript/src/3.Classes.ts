
interface Pingable{
    ping(): void
}

// Classe Point que implementa Pingable
class Point implements Pingable{
    private _x: number = 0
    private _y: number = 0
    protected readonly class_name: string = "Class name" // Constante / valor final

    // construtor de classe
    constructor(x?: number, y?: number, class_name?: string){
        this._x = x??0
        this._y = y??0

        this.class_name = class_name??"Class Point()" // Can edit readonly/Final value in constructor
    }

    // geters and setters
    get x(): number {return this._x}
    get y(): number {return this._y}
    set x(x: number) {this._x = x}
    set y(y: number) {this._y = y}

    typeOf = () => {return this.class_name} // retorna o tipo da classe
    position = () => {return [this._x, this._y]} // retorna valores de atributo X e Y
    ping(): void {console.log("ping")}

    // metodo estatico. pode ser usado sem a instancia da classe
    static help(){return "atr: _x, _y, _z / meth: typeOf, position, ping, getters and setters"}
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
        arr.push(this._z)
        return arr
    } // retorna valores de atributos X, Y, Z
}

// classe Box de tipo generico
class BoxCls <Box_Type>{
    _contents: Box_Type[] = [] // assimila tipo de valores para o array e inicia array vazio

    add = () => {this._contents.push()} // adiciona valor a array
    removeByTop = () => {this._contents.pop()}// remove ultimo valor de array
}

// instanciação das classes
let point = new Point(20,5)
console.log(point.position())

