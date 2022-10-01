console.log("Running index.ts");


//cannot change type of variables
// -- typing variables
let any_var: any; //any type
let string_array_var: String[]; //array of strings type
let number_or_string_var: number | String; //number or string type
let number_var = 0; //only number type


// -- typing args and returns in functions
let sum_func = (x:number, y:number):number=>{return x+y}


// --creating types / interfaces
type Triangle = {
    angle: number,
    type: "isosceles"|"scalene"|"obtuse" // predefined types: isosceles,scalene,obtuse / ENUM like
}
type Circle = {
    radius: number,
    border: "bordered"|"unbordered" // predefined types: bordered or unbordered // ENUM like
}
type Rect = {
    width?: number, // ? : optional argument 
    height?: number // ? : optional argument 
}
type GeometricFigure = Rect | Triangle | Circle; // predefined types: Rect, Triangle, Circle / ENUM like
// using created type "Rect"
let rect: GeometricFigure = {
    width: 20,
}
