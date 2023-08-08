"use strict";
console.log("Running index.ts");
//cannot change type of variables
// -- typing variables
let any_var; //any type
let string_array_var; //array of strings type
let number_or_string_var; //number or string type
let number_var = 0; //only number type
// -- typing args and returns in functions
let sum_func = (x, y) => { return x + y; };
// using created type "Rect"
let rect = {
    width: 20,
};
