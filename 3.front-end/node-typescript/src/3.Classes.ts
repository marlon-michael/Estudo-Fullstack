
interface Pingable{
    ping(): void
}

// -- class of Point
class Point implements Pingable{
    private _x: number = 0
    private _y: number = 0
    protected readonly class_name: string = "Class name" // Final value of var

    // -- instance constructor
    constructor(x?: number, y?: number, class_name?: string){
        this._x = x??0
        this._y = y??0

        this.class_name = class_name??"Class Point()" // Can edit readonly/Final value in constructor
    }

    // -- geters and setters
    get x(): number {return this._x}
    get y(): number {return this._y}
    set x(x: number) {this._x = x}
    set y(y: number) {this._y = y}

    typeOf = () => {return this.class_name}
    position = () => {return [this._x, this._y]}

    // -- static method can be used without a instance
    static help(){return "atr: _x, _y, _z / meth: typeOf, position, ping, getters and setters"}
    
    ping(): void {console.log("ping")}
}

class DDDPoint extends Point{
    _z: number = 0

    constructor(x?: number, y?: number, z?: number, class_name?: string){
        super(x, y, class_name)
        this._z = z??0
    }
    typeOf = () => {return super.typeOf()}
    position = () => {
        let arr = super.position()
        arr.push(this._z)
        return arr
    }
}

class BoxCls <Box_Type>{
    _contents: Box_Type[] = []

    add = () => {this._contents.push()}
    removeByTop = () => {this._contents.pop()}
}

let point = new Point(20,5)
console.log(point.position())

